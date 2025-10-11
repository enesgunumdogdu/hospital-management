<template>
  <div class="examination-form">
    <h1>{{ isEditMode ? 'Update Examination' : 'Add Examination' }}</h1>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label>Doctor:</label>
        <select v-model="form.doctorId" required>
          <option value="">Select Doctor</option>
          <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">
            {{ doctor.firstNameAndLastName }} ({{ doctor.specialty }})
          </option>
        </select>
      </div>
      
      <div class="form-group">
        <label>Patient:</label>
        <select v-model="form.patientId" required>
          <option value="">Select Patient</option>
          <option v-for="patient in patients" :key="patient.id" :value="patient.id">
            {{ patient.firstNameAndLastName }} ({{ patient.identityNumber }})
          </option>
        </select>
      </div>
      
      <div class="form-group">
        <label>Examination Date:</label>
        <input 
          v-model="form.examinationDate" 
          type="datetime-local" 
          required
        />
      </div>
      
      <div class="form-group">
        <label>Diagnosis:</label>
        <textarea 
          v-model="form.diagnosis" 
          required
          placeholder="Enter diagnosis"
          rows="4"
        ></textarea>
      </div>
      
      <div class="form-actions">
        <button type="submit" class="btn-success">
          {{ isEditMode ? 'Update' : 'Save' }}
        </button>
        <button type="button" @click="handleCancel" class="btn-primary">
          Cancel
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import examinationService from '@/services/examinationService'
import doctorService from '@/services/doctorService'
import patientService from '@/services/patientService'

export default {
  name: 'ExaminationForm',
  
  props: {
    examinationId: {
      type: Number,
      default: null
    }
  },
  
  data() {
    return {
      form: {
        doctorId: '',
        patientId: '',
        examinationDate: '',
        diagnosis: ''
      },
      doctors: [],
      patients: [],
      error: null,
      isEditMode: false
    }
  },
  
  mounted() {
    this.loadDoctors()
    this.loadPatients()
    
    if (this.examinationId) {
      this.isEditMode = true
      this.loadExamination()
    }
  },
  
  methods: {
    async loadDoctors() {
      try {
        const response = await doctorService.getAllDoctors()
        this.doctors = response.data
      } catch (err) {
        console.error('Failed to load doctors:', err)
      }
    },
    
    async loadPatients() {
      try {
        const response = await patientService.getAllPatients()
        this.patients = response.data
      } catch (err) {
        console.error('Failed to load patients:', err)
      }
    },
    
    async loadExamination() {
      try {
        const response = await examinationService.getExaminationById(this.examinationId)
        const exam = response.data
        
        this.form.doctorId = exam.doctor?.id || ''
        this.form.patientId = exam.patient?.id || ''
        this.form.examinationDate = this.formatDateTimeForInput(exam.examinationDate)
        this.form.diagnosis = exam.diagnosis
      } catch (err) {
        this.error = 'Failed to load examination: ' + err.message
      }
    },
    
    async handleSubmit() {
      this.error = null
      
      const payload = {
        doctor: { id: this.form.doctorId },
        patient: { id: this.form.patientId },
        examinationDate: this.form.examinationDate,
        diagnosis: this.form.diagnosis
      }
      
      try {
        if (this.isEditMode) {
          await examinationService.updateExamination(this.examinationId, payload)
        } else {
          await examinationService.createExamination(payload)
        }
        
        this.$emit('success')
        this.resetForm()
      } catch (err) {
        this.error = 'Failed to save examination: ' + err.message
      }
    },
    
    handleCancel() {
      this.$emit('cancel')
      this.resetForm()
    },
    
    resetForm() {
      this.form = {
        doctorId: '',
        patientId: '',
        examinationDate: '',
        diagnosis: ''
      }
    },
    
    formatDateTimeForInput(dateTime) {
      if (!dateTime) return ''
      const date = new Date(dateTime)
      const year = date.getFullYear()
      const month = String(date.getMonth() + 1).padStart(2, '0')
      const day = String(date.getDate()).padStart(2, '0')
      const hours = String(date.getHours()).padStart(2, '0')
      const minutes = String(date.getMinutes()).padStart(2, '0')
      return `${year}-${month}-${day}T${hours}:${minutes}`
    }
  }
}
</script>

<style scoped>
.examination-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

form {
  background-color: #f9f9f9;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600;
  color: #333;
}

input, select, textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
  font-family: inherit;
}

input:focus, select:focus, textarea:focus {
  outline: none;
  border-color: #6c757d;
}

textarea {
  resize: vertical;
  min-height: 100px;
}

.form-actions {
  display: flex;
  gap: 10px;
  margin-top: 30px;
}

.form-actions button {
  flex: 1;
}
</style>

