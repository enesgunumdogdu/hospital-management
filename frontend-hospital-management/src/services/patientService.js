import api from './api'

const patientService = {
  getAllPatients() {
    return api.get('/patients')
  },

  getPatientById(id) {
    return api.get(`/patients/${id}`)
  },

  createPatient(patient) {
    return api.post('/patients', patient)
  },

  updatePatient(id, patient) {
    return api.put(`/patients/${id}`, patient)
  },

  deletePatient(id) {
    return api.delete(`/patients/${id}`)
  }
}

export default patientService

