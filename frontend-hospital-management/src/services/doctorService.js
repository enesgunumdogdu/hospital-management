import api from './api'

const doctorService = {
  getAllDoctors() {
    return api.get('/doctors')
  },

  getDoctorById(id) {
    return api.get(`/doctors/${id}`)
  },

  createDoctor(doctor) {
    return api.post('/doctors', doctor)
  },

  updateDoctor(id, doctor) {
    return api.put(`/doctors/${id}`, doctor)
  },

  deleteDoctor(id) {
    return api.delete(`/doctors/${id}`)
  }
}

export default doctorService