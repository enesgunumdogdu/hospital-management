<template>
  <div class="prescription-list">
    <h1>Prescription List</h1>
    
    <div class="actions">
      <button @click="showAddForm" class="btn-success">Add Prescription</button>
      <button @click="loadPrescriptions" class="btn-primary">Refresh</button>
    </div>
    
    <div v-if="loading" class="loading">Loading...</div>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <div v-if="showForm" class="modal">
      <div class="modal-content">
        <PrescriptionForm 
          :prescriptionId="selectedPrescriptionId"
          @success="handleFormSuccess"
          @cancel="closeForm"
        />
      </div>
    </div>
    
    <table v-if="!loading && prescriptions.length > 0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Examination ID</th>
          <th>Medication Name</th>
          <th>Dosage</th>
          <th>Description</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="prescription in prescriptions" :key="prescription.id">
          <td>{{ prescription.id }}</td>
          <td>{{ prescription.examinationId || 'N/A' }}</td>
          <td>{{ prescription.medicationName }}</td>
          <td>{{ prescription.dosage }}</td>
          <td>{{ prescription.description || 'N/A' }}</td>
          <td>
            <button @click="editPrescription(prescription.id)" class="btn-primary">Edit</button>
            <button @click="deletePrescription(prescription.id)" class="btn-danger">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <div v-if="!loading && prescriptions.length === 0" class="no-data">
      No prescriptions found.
    </div>
  </div>
</template>

<script>
import prescriptionService from '@/services/prescriptionService'
import PrescriptionForm from './PrescriptionForm.vue'

export default {
  name: 'PrescriptionList',
  
  components: {
    PrescriptionForm
  },
  
  data() {
    return {
      prescriptions: [],
      loading: false,
      error: null,
      showForm: false,
      selectedPrescriptionId: null
    }
  },
  
  mounted() {
    this.loadPrescriptions()
  },
  
  methods: {
    async loadPrescriptions() {
      this.loading = true
      this.error = null
      
      try {
        const response = await prescriptionService.getAllPrescriptions()
        this.prescriptions = response.data
      } catch (err) {
        this.error = 'Failed to load prescriptions: ' + err.message
        console.error('Error loading prescriptions:', err)
      } finally {
        this.loading = false
      }
    },
    
    async deletePrescription(id) {
      if (!confirm('Are you sure you want to delete this prescription?')) {
        return
      }
      
      try {
        await prescriptionService.deletePrescription(id)
        this.loadPrescriptions()
      } catch (err) {
        this.error = 'Failed to delete prescription: ' + err.message
        console.error('Error deleting prescription:', err)
      }
    },
    
    showAddForm() {
      this.selectedPrescriptionId = null
      this.showForm = true
    },
    
    editPrescription(id) {
      this.selectedPrescriptionId = id
      this.showForm = true
    },
    
    handleFormSuccess() {
      this.closeForm()
      this.loadPrescriptions()
    },
    
    closeForm() {
      this.showForm = false
      this.selectedPrescriptionId = null
    }
  }
}
</script>

<style scoped>
.prescription-list {
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

