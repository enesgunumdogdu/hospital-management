<template>
  <div class="doctor-form">
    <h1>{{ isEditMode ? 'Update Doctor' : 'Add Doctor' }}</h1>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label>Name:</label>
        <input 
          v-model="form.firstNameAndLastName" 
          type="text" 
          required
          placeholder="Enter doctor name"
        />
      </div>
      
      <div class="form-group">
        <label>Specialty:</label>
        <input 
          v-model="form.specialty" 
          type="text" 
          required
          placeholder="Enter specialty"
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
import doctorService from '@/services/doctorService'

export default {
  name: 'DoctorForm',
  
  props: {
    doctorId: {
      type: Number,
      default: null
    }
  },
  
  data() {
    return {
      form: {
        firstNameAndLastName: '',
        specialty: '',
        phoneNumber: ''
      },
      error: null,
      isEditMode: false
    }
  },
  
  mounted() {
    if (this.doctorId) {
      this.isEditMode = true
      this.loadDoctor()
    }
  },
  
  methods: {
    async loadDoctor() {
      try {
        const response = await doctorService.getDoctorById(this.doctorId)
        this.form = { ...response.data }
      } catch (err) {
        this.error = 'Failed to load doctor: ' + err.message
      }
    },
    
    async handleSubmit() {
      this.error = null
      
      try {
        if (this.isEditMode) {
          await doctorService.updateDoctor(this.doctorId, this.form)
        } else {
          await doctorService.createDoctor(this.form)
        }
        
        this.$emit('success')
        this.resetForm()
      } catch (err) {
        this.error = 'Failed to save doctor: ' + err.message
      }
    },
    
    handleCancel() {
      this.$emit('cancel')
      this.resetForm()
    },
    
    resetForm() {
      this.form = {
        firstNameAndLastName: '',
        specialty: '',
        phoneNumber: ''
      }
    }
  }
}
</script>

<style scoped>
.doctor-form {
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

