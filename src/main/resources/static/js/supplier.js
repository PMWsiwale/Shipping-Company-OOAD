document.getElementById("supplierForm").addEventListener("submit", function (e) {
  e.preventDefault(); // Prevent actual form submission

  // Simulate submission logic
  alert("Registration successful! Welcome to FRAGIP LOGISTICS.");

  // Reset form (optional)
  this.reset();
});
