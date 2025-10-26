<script>
import DoctorList from './views/DoctorList.vue'
import PatientList from './views/PatientList.vue'
import ExaminationList from './views/ExaminationList.vue'
import PrescriptionList from './views/PrescriptionList.vue'
import Register from './views/Register.vue'
import authService from './services/authService'

export default {
  components: {
    DoctorList,
    PatientList,
    ExaminationList,
    PrescriptionList,
    Register
  },
  
  data() {
    return {
      isAuthenticated: false,
      currentView: 'doctors',
      userData: null
    }
  },
  
  mounted() {
    this.checkAuthentication()
    this.setupAuthListener()
  },
  
  beforeUnmount() {
    window.removeEventListener('auth:unauthorized', this.handleUnauthorized)
  },
  
  methods: {
    checkAuthentication() {
      this.isAuthenticated = authService.isAuthenticated()
      if (this.isAuthenticated) {
        this.userData = authService.getUserData()
      }
    },
    
    setupAuthListener() {
      window.addEventListener('auth:unauthorized', this.handleUnauthorized)
    },
    
    handleUnauthorized() {
      this.isAuthenticated = false
      this.userData = null
      this.currentView = 'doctors'
    },
    
    handleRegistered() {
      this.isAuthenticated = true
      this.userData = authService.getUserData()
      this.currentView = 'doctors'
    },
    
    handleLogout() {
      authService.logout()
      this.isAuthenticated = false
      this.userData = null
      this.currentView = 'doctors'
    },
    
    showView(view) {
      this.currentView = view
    }
  }
}
</script>

<template>
  <div id="app">
    <Register 
      v-if="!isAuthenticated" 
      @registered="handleRegistered"
    />
    
    <template v-else>
      <header>
        <div class="header-content">
          <h1>Hospital Management System</h1>
          <div class="user-info">
            <span>Welcome, {{ userData?.username }}</span>
            <button @click="handleLogout" class="btn-logout">Logout</button>
          </div>
        </div>
        <nav>
          <button 
            @click="showView('doctors')" 
            :class="{ active: currentView === 'doctors' }"
          >
            Doctors
          </button>
          <button 
            @click="showView('patients')" 
            :class="{ active: currentView === 'patients' }"
          >
            Patients
          </button>
          <button 
            @click="showView('examinations')" 
            :class="{ active: currentView === 'examinations' }"
          >
            Examinations
          </button>
          <button 
            @click="showView('prescriptions')" 
            :class="{ active: currentView === 'prescriptions' }"
          >
            Prescriptions
          </button>
        </nav>
      </header>
      
      <main>
        <DoctorList v-if="currentView === 'doctors'" />
        <PatientList v-if="currentView === 'patients'" />
        <ExaminationList v-if="currentView === 'examinations'" />
        <PrescriptionList v-if="currentView === 'prescriptions'" />
      </main>
    </template>
  </div>
</template>

<style scoped>
#app {
  min-height: 100vh;
}

header {
  background-color: #6c757d;
  color: white;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1400px;
  margin: 0 auto 15px auto;
}

header h1 {
  margin: 0;
  color: white;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 15px;
}

.user-info span {
  font-size: 14px;
  color: rgba(255, 255, 255, 0.9);
}

.btn-logout {
  background-color: #dc3545;
  color: white;
  padding: 8px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.2s;
}

.btn-logout:hover {
  background-color: #c82333;
}

nav {
  display: flex;
  justify-content: center;
  gap: 10px;
}

nav button {
  background-color: rgba(255, 255, 255, 0.2);
  color: white;
  border: 1px solid rgba(255, 255, 255, 0.3);
  padding: 10px 30px;
}

nav button:hover {
  background-color: rgba(255, 255, 255, 0.3);
}

nav button.active {
  background-color: white;
  color: #6c757d;
  font-weight: 600;
}

main {
  padding: 20px;
}
</style>
