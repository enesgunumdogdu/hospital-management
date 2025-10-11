<template>
  <div class="doctor-list">
    <h1>Doctor List</h1>
    
    <div class="actions">
      <button @click="showAddForm" class="btn-success">Add Doctor</button>
      <button @click="loadDoctors" class="btn-primary">Refresh</button>
    </div>
    
    <div v-if="loading" class="loading">Loading...</div>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <div v-if="showForm" class="modal">
      <div class="modal-content">
        <DoctorForm 
          :doctorId="selectedDoctorId"
          @success="handleFormSuccess"
          @cancel="closeForm"
        />
      </div>
    </div>
    
    <table v-if="!loading && doctors.length > 0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Specialty</th>
          <th>Phone</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="doctor in doctors" :key="doctor.id">
          <td>{{ doctor.id }}</td>
          <td>{{ doctor.firstNameAndLastName }}</td>
          <td>{{ doctor.specialty }}</td>
          <td>{{ doctor.phoneNumber }}</td>
          <td>
            <button @click="editDoctor(doctor.id)" class="btn-primary">Edit</button>
            <button @click="deleteDoctor(doctor.id)" class="btn-danger">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <div v-if="!loading && doctors.length === 0" class="no-data">
      No doctors found.
    </div>
  </div>
</template>

<script>
import doctorService from '@/services/doctorService'
import DoctorForm from './DoctorForm.vue'

export default {
  name: 'DoctorList',
  
  components: {
    DoctorForm
  },
  
  data() {
    return {
      doctors: [],
      loading: false,
      error: null,
      showForm: false,
      selectedDoctorId: null
    }
  },
  
  mounted() {
    this.loadDoctors()
  },
  
  methods: {
    async loadDoctors() {
      this.loading = true
      this.error = null
      
      try {
        const response = await doctorService.getAllDoctors()
        this.doctors = response.data
      } catch (err) {
        this.error = 'Failed to load doctors: ' + err.message
        console.error('Error loading doctors:', err)
      } finally {
        this.loading = false
      }
    },
    
    async deleteDoctor(id) {
      if (!confirm('Are you sure you want to delete this doctor?')) {
        return
      }
      
      try {
        await doctorService.deleteDoctor(id)
        this.loadDoctors()
      } catch (err) {
        this.error = 'Failed to delete doctor: ' + err.message
        console.error('Error deleting doctor:', err)
      }
    },
    
    showAddForm() {
      this.selectedDoctorId = null
      this.showForm = true
    },
    
    editDoctor(id) {
      this.selectedDoctorId = id
      this.showForm = true
    },
    
    handleFormSuccess() {
      this.closeForm()
      this.loadDoctors()
    },
    
    closeForm() {
      this.showForm = false
      this.selectedDoctorId = null
    }
  }
}
</script>

<style scoped>
.doctor-list {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.actions {
  margin-bottom: 20px;
  display: flex;
  gap: 10px;
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: white;
  border-radius: 8px;
  max-width: 700px;
  width: 90%;
  max-height: 90vh;
  overflow-y: auto;
}
</style>

