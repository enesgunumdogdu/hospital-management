import api from './api'

const authService = {
    register(user) {
        return api.post('/auth/register', user)
    },

    login(credentials) {
        return api.post('/auth/login', credentials)
    },

    setToken(token) {
        localStorage.setItem('token', token)
        api.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    
    getToken() {
        return localStorage.getItem('token')
    },
    
    removeToken() {
        localStorage.removeItem('token')
        delete api.defaults.headers.common['Authorization']
    },
    
    isAuthenticated() {
        return !!this.getToken()
    },

    setUserData(userData) {
        localStorage.setItem('userData', JSON.stringify(userData))
    },

    getUserData() {
        const userData = localStorage.getItem('userData')
        return userData ? JSON.parse(userData) : null
    },

    logout() {
        this.removeToken()
        localStorage.removeItem('userData')
    }
}

export default authService
