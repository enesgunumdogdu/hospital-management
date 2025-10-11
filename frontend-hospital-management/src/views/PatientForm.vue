<template>
  <div class="patient-form">
    <h1>{{ isEditMode ? 'Update Patient' : 'Add Patient' }}</h1>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label>Name:</label>
        <input 
          v-model="form.firstNameAndLastName" 
          type="text" 
          required
          placeholder="Enter patient name"
        />
      </div>
      
      <div class="form-group">
        <label>Identity Number:</label>
        <input 
          v-model="form.identityNumber" 
          type="text" 
          required
          pattern="[0-9]{11}"
          placeholder="11 digits"
          maxlength="11"
        />
      </div>
      
      <div class="form-group">
        <label>Birth Date:</label>
        <input 
          v-model="form.birthDate" 
          type="date" 
          required
        />
      </div>
      
      <div class="form-group">
        <label>Phone:</label>
        <input 
          v-model="form.phoneNumber" 
          type="text" 
          required
          placeholder="Enter phone number"
        />
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
import patientService from '@/services/patientService'

export default {
  name: 'PatientForm',
  
  props: {
    patientId: {
      type: Number,
      default: null
    }
  },
  
  data() {
    return {
      form: {
        firstNameAndLastName: '',
        identityNumber: '',
        birthDate: '',
        phoneNumber: ''
      },
      error: null,
      isEditMode: false
    }
  },
  
  mounted() {
    if (this.patientId) {
      this.isEditMode = true
      this.loadPatient()
    }
  },
  
  methods: {
    async loadPatient() {
      try {
        const response = await patientService.getPatientById(this.patientId)
        this.form = { ...response.data }
      } catch (err) {
        this.error = 'Failed to load patient: ' + err.message
      }
    },
    
    async handleSubmit() {
      this.error = null
      
      try {
        if (this.isEditMode) {
          await patientService.updatePatient(this.patientId, this.form)
        } else {
          await patientService.createPatient(this.form)
        }
        
        this.$emit('success')
        this.resetForm()
      } catch (err) {
        this.error = 'Failed to save patient: ' + err.message
      }
    },
    
    handleCancel() {
      this.$emit('cancel')
      this.resetForm()
    },
    
    resetForm() {
      this.form = {
        firstNameAndLastName: '',
        identityNumber: '',
        birthDate: '',
        phoneNumber: ''
      }
    }
  }
}
</script>

<style scoped>
.patient-form {
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

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

input:focus {
  outline: none;
  border-color: #6c757d;
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

