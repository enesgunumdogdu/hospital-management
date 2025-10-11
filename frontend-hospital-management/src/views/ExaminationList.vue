<template>
  <div class="examination-list">
    <h1>Examination List</h1>
    
    <div class="actions">
      <button @click="showAddForm" class="btn-success">Add Examination</button>
      <button @click="loadExaminations" class="btn-primary">Refresh</button>
    </div>
    
    <div v-if="loading" class="loading">Loading...</div>
    
    <div v-if="error" class="error">{{ error }}</div>
    
    <div v-if="showForm" class="modal">
      <div class="modal-content">
        <ExaminationForm 
          :examinationId="selectedExaminationId"
          @success="handleFormSuccess"
          @cancel="closeForm"
        />
      </div>
    </div>
    
    <table v-if="!loading && examinations.length > 0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Doctor</th>
          <th>Patient</th>
          <th>Date</th>
          <th>Diagnosis</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="examination in examinations" :key="examination.id">
          <td>{{ examination.id }}</td>
          <td>{{ examination.doctor?.firstNameAndLastName || 'N/A' }}</td>
          <td>{{ examination.patient?.firstNameAndLastName || 'N/A' }}</td>
          <td>{{ formatDateTime(examination.examinationDate) }}</td>
          <td>{{ examination.diagnosis }}</td>
          <td>
            <button @click="editExamination(examination.id)" class="btn-primary">Edit</button>
            <button @click="deleteExamination(examination.id)" class="btn-danger">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    
    <div v-if="!loading && examinations.length === 0" class="no-data">
      No examinations found.
    </div>
  </div>
</template>

<script>
import examinationService from '@/services/examinationService'
import ExaminationForm from './ExaminationForm.vue'

export default {
  name: 'ExaminationList',
  
  components: {
    ExaminationForm
  },
  
  data() {
    return {
      examinations: [],
      loading: false,
      error: null,
      showForm: false,
      selectedExaminationId: null
    }
  },
  
  mounted() {
    this.loadExaminations()
  },
  
  methods: {
    async loadExaminations() {
      this.loading = true
      this.error = null
      
      try {
        const response = await examinationService.getAllExaminations()
        this.examinations = response.data
      } catch (err) {
        this.error = 'Failed to load examinations: ' + err.message
        console.error('Error loading examinations:', err)
      } finally {
        this.loading = false
      }
    },
    
    async deleteExamination(id) {
      if (!confirm('Are you sure you want to delete this examination?')) {
        return
      }
      
      try {
        await examinationService.deleteExamination(id)
        this.loadExaminations()
      } catch (err) {
        this.error = 'Failed to delete examination: ' + err.message
        console.error('Error deleting examination:', err)
      }
    },
    
    showAddForm() {
      this.selectedExaminationId = null
      this.showForm = true
    },
    
    editExamination(id) {
      this.selectedExaminationId = id
      this.showForm = true
    },
    
    handleFormSuccess() {
      this.closeForm()
      this.loadExaminations()
    },
    
    closeForm() {
      this.showForm = false
      this.selectedExaminationId = null
    },
    
    formatDateTime(dateTime) {
      if (!dateTime) return 'N/A'
      return new Date(dateTime).toLocaleString('tr-TR')
    }
  }
}
</script>

<style scoped>
.examination-list {
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

