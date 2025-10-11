<template>
  <div class="prescription-form">
    <h1>{{ isEditMode ? 'Update Prescription' : 'Add Prescription' }}</h1>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label>Examination:</label>
        <select v-model="form.examinationId" required>
          <option value="">Select Examination</option>
          <option v-for="exam in examinations" :key="exam.id" :value="exam.id">
            ID: {{ exam.id }} - {{ exam.doctor?.firstNameAndLastName }} - {{ exam.patient?.firstNameAndLastName }}
          </option>
        </select>
      </div>
      
      <div class="form-group">
        <label>Medication Name:</label>
        <input 
          v-model="form.medicationName" 
          type="text" 
          required
          placeholder="Enter medication name"
        />
      </div>
      
      <div class="form-group">
        <label>Dosage:</label>
        <input 
          v-model="form.dosage" 
          type="text" 
          required
          placeholder="e.g., 2x1, 3x500mg"
        />
      </div>
      
      <div class="form-group">
        <label>Description:</label>
        <textarea 
          v-model="form.description" 
          placeholder="Usage instructions (optional)"
          rows="3"
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
import prescriptionService from '@/services/prescriptionService'
import examinationService from '@/services/examinationService'

export default {
  name: 'PrescriptionForm',
  
  props: {
    prescriptionId: {
      type: Number,
      default: null
    }
  },
  
  data() {
    return {
      form: {
        examinationId: '',
        medicationName: '',
        dosage: '',
        description: ''
      },
      examinations: [],
      error: null,
      isEditMode: false
    }
  },
  
  mounted() {
    this.loadExaminations()
    
    if (this.prescriptionId) {
      this.isEditMode = true
      this.loadPrescription()
    }
  },
  
  methods: {
    async loadExaminations() {
      try {
        const response = await examinationService.getAllExaminations()
        this.examinations = response.data
      } catch (err) {
        console.error('Failed to load examinations:', err)
      }
    },
    
    async loadPrescription() {
      try {
        const response = await prescriptionService.getPrescriptionById(this.prescriptionId)
        const pres = response.data
        
        this.form.examinationId = pres.examinationId || ''
        this.form.medicationName = pres.medicationName
        this.form.dosage = pres.dosage
        this.form.description = pres.description || ''
      } catch (err) {
        this.error = 'Failed to load prescription: ' + err.message
      }
    },
    
    async handleSubmit() {
      this.error = null
      
      const payload = {
        examinationId: this.form.examinationId,
        medicationName: this.form.medicationName,
        dosage: this.form.dosage,
        description: this.form.description
      }
      
      try {
        if (this.isEditMode) {
          await prescriptionService.updatePrescription(this.prescriptionId, payload)
        } else {
          await prescriptionService.createPrescription(payload)
        }
        
        this.$emit('success')
        this.resetForm()
      } catch (err) {
        this.error = 'Failed to save prescription: ' + err.message
      }
    },
    
    handleCancel() {
      this.$emit('cancel')
      this.resetForm()
    },
    
    resetForm() {
      this.form = {
        examinationId: '',
        medicationName: '',
        dosage: '',
        description: ''
      }
    }
  }
}
</script>

<style scoped>
.prescription-form {
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

