document.addEventListener("DOMContentLoaded", function () {
    const sections = document.querySelectorAll(".dashboard-section");

    // Hide all sections except Home on load
    sections.forEach(section => section.style.display = "none");
    document.getElementById("home").style.display = "block"; 

    document.querySelectorAll('.sidebar-menu a, .nav-links a').forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            
            const sectionID = this.getAttribute('href').substring(1); // Get section ID
            sections.forEach(section => section.style.display = "none"); // Hide all sections

            const targetSection = document.getElementById(sectionID);
            if (targetSection) {
                targetSection.style.display = "block";
            }

            // Highlight active menu item
            document.querySelectorAll('.sidebar-menu a, .nav-links a').forEach(item => item.classList.remove('active'));
            this.classList.add('active');

            // Ensure Home section remains visible when clicking Dashboard
            if (sectionID === "dashboard-content") {
                document.getElementById("home").style.display = "block"; 
            }
        });
    });
});

// Supplier List Display
function displaySuppliers() {
    const dashboardContent = document.getElementById("dashboard-content");
    dashboardContent.innerHTML = `
    <h2>👔 Registered Suppliers</h2>
    <p>Here are some suppliers associated with the company:</p>
    <ul class="supplier-list">
        ${["ABC Ltd.", "XYZ Enterprises", "Global Supplies", "Tech Distribution Co.", "FastTrack Logistics"]
            .map(name => `<li>${name}</li>`)
            .join("")}
    </ul>`;
}

// Reports Generation Section
function displayReports() {
    const dashboardContent = document.getElementById("dashboard-content");
    dashboardContent.innerHTML = `
    <h2>📊 Generate Reports</h2>
    <p>Select the report type and date range:</p>
    <label for="report-type">Report Type:</label>
    <select id="report-type">
        <option value="inventory">Inventory Report</option>
        <option value="sales">Sales Report</option>
        <option value="supplier">Supplier Report</option>
    </select>
    <label for="report-date">Date Range:</label>
    <input type="date" id="report-date">
    <button class="button success" onclick="generateReport()">Generate Report</button>
    <p id="report-status"></p>`;
}

// Settings Customization Section
function displaySettings() {
    const dashboardContent = document.getElementById("dashboard-content");
    dashboardContent.innerHTML = `
    <h2>⚙️ Settings</h2>
    <p>Adjust your preferences:</p>
    <label for="theme-toggle">Theme:</label>
    <select id="theme-toggle">
        <option value="light">Light Mode</option>
        <option value="dark">Dark Mode</option>
    </select>
    <button class="button success" onclick="applySettings()">Apply Settings</button>
    <p id="settings-status"></p>`;
}

// Invoice Approval Section
function displayInvoiceApproval() {
    const dashboardContent = document.getElementById("dashboard-content");
    dashboardContent.innerHTML = `
    <h2>📜 Invoice Approval</h2>
    <label for="invoice-id">Invoice ID:</label>
    <input type="number" id="invoice-id">
    <button class="button success">Approve Invoice</button>
    <p id="approval-status"></p>`;
}

// Approved Invoices Section
function displayApprovedInvoices() {
    const dashboardContent = document.getElementById("dashboard-content");
    dashboardContent.innerHTML = `
    <h2>✅ Approved Invoices</h2>
    <table class="data-table">
        <thead>
            <tr>
                <th>Invoice ID</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Mark</td>
                <td>50</td>
                <td>Approved</td>
            </tr>
        </tbody>
    </table>`;
}

// Recent Invoices Section
function displayRecentInvoices() {
    const dashboardContent = document.getElementById("dashboard-content");
    dashboardContent.innerHTML = `
    <h2>📂 Recent Invoices</h2>
    <table class="data-table">
        <thead>
            <tr>
                <th>Invoice ID</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>2</td>
                <td>John</td>
                <td>30</td>
                <td>Pending</td>
            </tr>
        </tbody>
    </table>`;
}
