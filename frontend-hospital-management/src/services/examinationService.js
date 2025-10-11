import api from './api'

const examinationService = {
  getAllExaminations() {
    return api.get('/examinations')
  },

  getExaminationById(id) {
    return api.get(`/examinations/${id}`)
  },

  createExamination(examination) {
    return api.post('/examinations', examination)
  },

  updateExamination(id, examination) {
    return api.put(`/examinations/${id}`, examination)
  },

  deleteExamination(id) {
    return api.delete(`/examinations/${id}`)
  }
}

export default examinationService

