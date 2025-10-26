<template>
  <div class="auth-container">
    <div class="auth-box">
      <h1>Register</h1>
      
      <div v-if="error" class="error">{{ error }}</div>
      <div v-if="success" class="success">{{ success }}</div>
      
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label>Username:</label>
          <input 
            v-model="form.username" 
            type="text" 
            required
            minlength="3"
            placeholder="Enter username"
          />
        </div>
        
        <div class="form-group">
          <label>Email:</label>
          <input 
            v-model="form.email" 
            type="email" 
            required
            placeholder="Enter email"
          />
        </div>
        
        <div class="form-group">
          <label>Password:</label>
          <input 
            v-model="form.password" 
            type="password" 
            required
            minlength="6"
            placeholder="Minimum 6 characters"
          />
        </div>
        
        <div class="form-group">
          <label>Confirm Password:</label>
          <input 
            v-model="form.confirmPassword" 
            type="password" 
            required
            minlength="6"
            placeholder="Re-enter password"
          />
        </div>
        
        <div class="form-actions">
          <button type="submit" class="btn-success" :disabled="loading">
            {{ loading ? 'Registering...' : 'Register' }}
          </button>
        </div>
      </form>
      
      <div class="auth-link">
        Already have an account? <a href="#" @click.prevent="goToLogin">Login</a>
      </div>
    </div>
  </div>
</template>

<script>
import authService from '@/services/authService'

export default {
  name: 'Register',
  
  data() {
    return {
      form: {
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
      },
      error: '',
      success: '',
      loading: false
    }
  },
  
  methods: {
    validateForm() {
      if (this.form.username.length < 3) {
        this.error = 'Username must be at least 3 characters'
        return false
      }
      
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
      if (!emailRegex.test(this.form.email)) {
        this.error = 'Please enter a valid email address'
        return false
      }
      
      if (this.form.password.length < 6) {
        this.error = 'Password must be at least 6 characters'
        return false
      }
      
      if (this.form.password !== this.form.confirmPassword) {
        this.error = 'Passwords do not match'
        return false
      }
      
      return true
    },
    
    async handleSubmit() {
      this.error = ''
      this.success = ''
      
      if (!this.validateForm()) {
        return
      }
      
      this.loading = true
      
      try {
        const response = await authService.register({
          username: this.form.username,
          email: this.form.email,
          password: this.form.password
        })
        
        const { token, username, email } = response.data
        
        authService.setToken(token)
        authService.setUserData({ username, email })
        
        this.success = 'Registration successful! Redirecting...'
        
        setTimeout(() => {
          this.$emit('registered')
        }, 1000)
        
      } catch (err) {
        if (err.response?.data?.message) {
          this.error = err.response.data.message
        } else if (err.response?.status === 409) {
          this.error = 'Username or email already exists'
        } else {
          this.error = 'Registration failed. Please try again'
        }
      } finally {
        this.loading = false
      }
    },
    
    goToLogin() {
      this.$emit('go-to-login')
    }
  }
}
</script>

<style scoped>
.auth-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.auth-box {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 10px 25px rgba(0,0,0,0.2);
  width: 100%;
  max-width: 450px;
}

.auth-box h1 {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
  font-size: 28px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #555;
  font-weight: 500;
  font-size: 14px;
}

.form-group input {
  width: 100%;
  padding: 12px 15px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 14px;
  transition: border-color 0.3s;
  box-sizing: border-box;
}

.form-group input:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.form-actions {
  margin-top: 30px;
}

.form-actions button {
  width: 100%;
  padding: 12px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 6px;
}

.form-actions button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.success {
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
  color: #155724;
  padding: 12px;
  border-radius: 6px;
  margin-bottom: 20px;
  text-align: center;
}

.error {
  margin-bottom: 20px;
  text-align: center;
}

.auth-link {
  margin-top: 20px;
  text-align: center;
  color: #666;
  font-size: 14px;
}

.auth-link a {
  color: #667eea;
  text-decoration: none;
  font-weight: 600;
}

.auth-link a:hover {
  text-decoration: underline;
}
</style>

