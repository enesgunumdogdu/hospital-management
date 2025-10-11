<template>
  <div class="patient-list">
    <h1>Patient List</h1>
    
    <div class="actions">
      <button @click="showAddForm" class="btn-success">Add Patient</button>
      <button @click="loadPatients" class="btn-primary">Refresh</button>
    </div>
    
    <div v-if="loading" class="loading">Loading...</div>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <div v-if="showForm" class="modal">
      <div class="modal-content">
        <PatientForm 
          :patientId="selectedPatientId"
          @success="handleFormSuccess"
          @cancel="closeForm"
        />
      </div>
    </div>
    
    <table v-if="!loading && patients.length > 0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Identity Number</th>
          <th>Birth Date</th>
          <th>Phone</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="patient in patients" :key="patient.id">
          <td>{{ patient.id }}</td>
          <td>{{ patient.firstNameAndLastName }}</td>
          <td>{{ patient.identityNumber }}</td>
          <td>{{ patient.birthDate }}</td>
          <td>{{ patient.phoneNumber }}</td>
          <td>
            <button @click="editPatient(patient.id)" class="btn-primary">Edit</button>
            <button @click="deletePatient(patient.id)" class="btn-danger">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <div v-if="!loading && patients.length === 0" class="no-data">
      No patients found.
    </div>
  </div>
</template>

<script>
import patientService from '@/services/patientService'
import PatientForm from './PatientForm.vue'

export default {
  name: 'PatientList',
  
  components: {
    PatientForm
  },
  
  data() {
    return {
      patients: [],
      loading: false,
      error: null,
      showForm: false,
      selectedPatientId: null
    }
  },
  
  mounted() {
    this.loadPatients()
  },
  
  methods: {
    async loadPatients() {
      this.loading = true
      this.error = null
      
      try {
        const response = await patientService.getAllPatients()
        this.patients = response.data
      } catch (err) {
        this.error = 'Failed to load patients: ' + err.message
        console.error('Error loading patients:', err)
      } finally {
        this.loading = false
      }
    },
    
    async deletePatient(id) {
      if (!confirm('Are you sure you want to delete this patient?')) {
        return
      }
      
      try {
        await patientService.deletePatient(id)
        this.loadPatients()
      } catch (err) {
        this.error = 'Failed to delete patient: ' + err.message
        console.error('Error deleting patient:', err)
      }
    },
    
    showAddForm() {
      this.selectedPatientId = null
      this.showForm = true
    },
    
    editPatient(id) {
      this.selectedPatientId = id
      this.showForm = true
    },
    
    handleFormSuccess() {
      this.closeForm()
      this.loadPatients()
    },
    
    closeForm() {
      this.showForm = false
      this.selectedPatientId = null
    }
  }
}
</script>

<style scoped>
.patient-list {
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

