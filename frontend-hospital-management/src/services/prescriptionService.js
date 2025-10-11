import api from './api'

const prescriptionService = {
  getAllPrescriptions() {
    return api.get('/prescriptions')
  },

  getPrescriptionById(id) {
    return api.get(`/prescriptions/${id}`)
  },

  createPrescription(prescription) {
    return api.post('/prescriptions', prescription)
  },

  updatePrescription(id, prescription) {
    return api.put(`/prescriptions/${id}`, prescription)
  },

  deletePrescription(id) {
    return api.delete(`/prescriptions/${id}`)
  }
}

export default prescriptionService

