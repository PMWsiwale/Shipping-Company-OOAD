document.addEventListener("DOMContentLoaded", function () {
    const menuItems = document.querySelectorAll(".sidebar-menu a");
    const sections = document.querySelectorAll(".dashboard-section");

    // Hide all sections except Dashboard initially
    sections.forEach(section => section.style.display = "none");
    document.getElementById("dashboard").style.display = "block";

    menuItems.forEach(item => {
        item.addEventListener("click", function (event) {
            event.preventDefault();

            // Remove active class from all menu items & add to clicked one
            menuItems.forEach(menu => menu.classList.remove("active"));
            this.classList.add("active");

            // Hide all sections
            sections.forEach(section => section.style.display = "none");

            // Display selected section based on data-section attribute
            const sectionID = this.getAttribute("href").substring(1);
            const selectedSection = document.getElementById(sectionID);
            if (selectedSection) {
                selectedSection.style.display = "block";
            }

            // Ensure that specific items dynamically load content when clicked
            if (sectionID === "inventory-management") {
                displayInventoryRecords();
            }
            if (sectionID === "added-stock") {
                displayAddedStock();
            }
            if (sectionID === "record-stock") {
                displayRecordStock();
            }
            if (sectionID === "stock-clerk-details") {
                displayStockClerks();
            }
            if (sectionID === "reports") {
                displayReports();
            }
            if (sectionID === "settings") {
                displaySettings();
            }
        });
    });

    // **Inventory Records Display**


    // **Added Stock Display**
    function displayAddedStock() {
        document.getElementById("added-stock").innerHTML = `
        <h2>📦 Added Stock</h2>
        <table class="data-table">
            <thead>
                <tr><th>Item Name</th><th>Customer Name</th><th>Supplier</th><th>Quantity</th><th>Location</th><th>Stock Type</th></tr>
            </thead>
            <tbody>
                <tr><td>Item X</td><td>Customer Y</td><td>Supplier Z</td><td>10</td><td>Warehouse Y</td><td>Breakables</td></tr>
            </tbody>
        </table>`;
    }

    // **Record Stock Form**
    function displayRecordStock() {
        document.getElementById("record-stock").innerHTML = `
        <h2>📝 Record Stock</h2>
       <form class="record-stock-form">
                    <div class="form-group"><label>ITEM NAME:</label><input type="text" placeholder="Enter customer ID"></div>
                    <div class="form-group"><label>ITEM NAME:</label><input type="text" placeholder="Enter supplier name"></div>
                    <div class="form-group"><label>TYPE OF STOCK:</label>
                        <select><option>Select type</option><option>New Stock</option><option>Cancelled Stock</option><option>Returned Stock</option></select>
                    </div>
                    <div class="form-group"><label>DATE OF PURCHASE:</label><input type="text" placeholder="Enter item name"></div>
                    <div class="form-group"><label>BRAND NAME:</label><input type="text" placeholder="DOP"></div>
                    <div class="form-group"><label>CATEGORY:</label><input type="text" placeholder="brand name"></div>
                    <div class="form-group"><label>WEIGHT:</label><input type="number" placeholder="weight"></div>
                    <div class="form-group"><label>CATEGORY:</label><input type="number" placeholder="category"></div>
                    <div class="form-group"><label>CUSTOMER ID:</label><input type="number" placeholder="customer id"></div>
                    <div class="form-group"><label>SUPPLIER NAME :</label><input type="number" placeholder="supplier"></div>
                    <div class="form-group"><label>TYPE OF STOCK:</label><input type="number" placeholder="stock"></div>
                    
                    <button type="submit" class="button success">Save Stock Entry</button>
                </form>`;
    }

    // **Stock Clerks Display**
    function displayStockClerks() {
        document.getElementById("stock-clerk-details").innerHTML = `
        <h2>👔 Stock Clerk Details</h2>
        <form class="stock-clerk-form">
            <div class="form-group"><label>Username:</label><input type="text" placeholder="Enter username"></div>
            <div class="form-group"><label>Stock Clerk ID:</label><input type="text" placeholder="Enter ID"></div>
            <button type="submit" class="button success">Save</button>
        </form>`;
    }

    // **Reports Generation**
    function displayReports() {
        document.getElementById("reports").innerHTML = `
        <h2>📊 Reports</h2>
        <form class="report-form">
            <div class="form-group"><label>Select Report Type:</label>
                <select><option>Stock Summary</option><option>Supplier Transactions</option><option>Customer Purchases</option></select>
            </div>
            <div class="form-group"><label>Date Range:</label><input type="date"></div>
            <button type="button" class="button success">Generate Report</button>
        </form>`;
    }

    // **Settings Customization**
    function displaySettings() {
        document.getElementById("settings").innerHTML = `
        <h2>⚙️ Settings</h2>
        <form class="settings-form">
            <div class="form-group"><label>Select Theme:</label>
                <select><option>Light Mode</option><option>Dark Mode</option></select>
            </div>
            <div class="form-group"><label>Enable Notifications:</label><input type="checkbox" checked></div>
            <button type="button" class="button success">Save Settings</button>
        </form>`;
    }
});
