<script>
import DoctorList from './views/DoctorList.vue'
import PatientList from './views/PatientList.vue'
import ExaminationList from './views/ExaminationList.vue'
import PrescriptionList from './views/PrescriptionList.vue'

export default {
  components: {
    DoctorList,
    PatientList,
    ExaminationList,
    PrescriptionList
  },
  
  data() {
    return {
      currentView: 'doctors'
    }
  },
  
  methods: {
    showView(view) {
      this.currentView = view
    }
  }
}
</script>

<template>
  <div id="app">
    <header>
      <h1>Hospital Management System</h1>
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
  text-align: center;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

header h1 {
  margin: 0 0 15px 0;
  color: white;
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
