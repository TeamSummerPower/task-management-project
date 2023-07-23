const formOpenBtn = document.querySelector("#form-open"),
  home = document.querySelector(".home"),
  formContainer = document.querySelector(".form_container"),
  formCloseBtn = document.querySelector(".form_close"),
  signupBtn = document.querySelector("#signup"),
  loginBtn = document.querySelector("#login"),
  pwShowHide = document.querySelectorAll(".pw_hide"),
  loginForm = document.querySelector(".login_form form"),
  signupForm = document.querySelector(".signup_form form");
  const errorMessageLogin = document.getElementById("errorMessageLogin");
  const errorMessageSignUp = document.getElementById("errorMessageSignUp");
  const closeButton = document.getElementById("closeButton");

formOpenBtn.addEventListener("click", () => home.classList.add("show"));
formCloseBtn.addEventListener("click", () => home.classList.remove("show"));

pwShowHide.forEach((icon) => {
  icon.addEventListener("click", () => {
    let getPwInput = icon.parentElement.querySelector("input");
    if (getPwInput.type === "password") {
      getPwInput.type = "text";
      icon.classList.replace("uil-eye-slash", "uil-eye");
    } else {
      getPwInput.type = "password";
      icon.classList.replace("uil-eye", "uil-eye-slash");
    }
  });
});

closeButton.addEventListener("click", () => {
  loginForm.reset();
  signupForm.reset();
  errorMessageLogin.textContent = "";
  errorMessageSignUp.textContent = "";
});

signupBtn.addEventListener("click", (e) => {
  e.preventDefault();
  formContainer.classList.add("active");
});

loginBtn.addEventListener("click", (e) => {
  errorMessageLogin.textContent = "";
  e.preventDefault();
  formContainer.classList.remove("active");
});

// Function to handle form submissions
function handleFormSubmit(url, formData) {
  return fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(formData),
  }).then((response) => response.json());
}

loginForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const userName = loginForm.querySelector('input[type="email"]').value;
  const password = loginForm.querySelector('input[type="password"]').value;
  const formData = { userName, password };

  handleFormSubmit("http://localhost:8080/user/login", formData)
    .then((data) => {
      // Process the response from the backend (data) as needed
      // For example, display success message, redirect to a new page, etc.
      if (typeof data === "number") {
        // sending you to the main page
        // last line could be removed after we have the main page
        errorMessageLogin.textContent = "";
        window.location.href = "mainTaskPage/index.html";
        console.log(data);
      } else {
        errorMessageLogin.textContent = data;
      }
    })
    .catch((error) => {
      // Handle errors that may occur during the login process
      console.error("Login error:", error);
    });
});

signupForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const userName = signupForm.querySelector('input[type="email"]').value;
  const password = signupForm.querySelector('input[type="password"]').value;
  const confirmPassword = signupForm.querySelector('input[placeholder="Confirm password"]').value;
  
  if (password !== confirmPassword) {
    errorMessageSignUp.textContent = "Passwords do not match";
    return;
  }

  const formData = { userName, password };

  handleFormSubmit("http://localhost:8080/user/register", formData)
    .then((data) => {
      // Process the response from the backend (data) as needed
      // For example, display success message, redirect to a new page, etc.
      if (typeof data === "number") {
        // sending you to the main page
        // last line could be removed after we have the main page
        errorMessageSignUp.textContent = "";
        window.location.href = "mainTaskPage/index.html";
        console.log(data);
      } else {
        errorMessageSignUp.textContent = data;
      }
    })
    .catch((error) => {
      // Handle errors that may occur during the registration process
      console.error("Registration error:", error);
    });
});