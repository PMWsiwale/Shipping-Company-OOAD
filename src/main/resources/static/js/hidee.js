// Store orders data
const orders = {
    pending: [],
    shipped: []
};

// DOM Elements
const form = document.getElementById('shipment-form');
const toggleButton = document.getElementById('hide-button');
const formSection = document.getElementById('content');

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    // Add event listeners
    form.addEventListener('submit', handleSubmit);
    toggleButton.addEventListener('click', toggleForm);

    // Load any existing data (could be from localStorage)
    loadInitialData();

    // Update tables
    updateTables();
});

// Toggle form visibility
function toggleForm() {
    formSection.classList.toggle('hidden');
    toggleButton.textContent = formSection.classList.contains('hidden') ?
        '+ Add New Shipment' :
        'Cancel';
}

// Handle form submission
function handleSubmit(event) {
    event.preventDefault();

    // Get form values
    const formData = new FormData(form);
    const orderData = Object.fromEntries(formData.entries());

    // Generate order ID
    orderData.orderId = generateOrderId();

    // Add to pending orders
    orders.pending.push(orderData);

    // Update tables
    updateTables();

    // Reset form and hide it
    form.reset();
    formSection.classList.add('hidden');
    toggleButton.textContent = '+ Add New Shipment';
}

// Ship an order
function shipOrder(orderId) {
    // Find the order in pending
    const orderIndex = orders.pending.findIndex(order => order.orderId == orderId);

    if (orderIndex !== -1) {
        // Move to shipped
        const [shippedOrder] = orders.pending.splice(orderIndex, 1);
        orders.shipped.push(shippedOrder);

        // Update tables
        updateTables();
    }
}

// Generate a random order ID
function generateOrderId() {
    return Math.floor(1000 + Math.random() * 9000);
}

// Update both tables
function updateTables() {
    updatePendingTable();
    updateShippedTable();

    // In a real app, you might save to localStorage here
    // localStorage.setItem('orders', JSON.stringify(orders));
}

// Update pending orders table
function updatePendingTable() {
    const tbody = document.querySelector('#pending-orders tbody');
    tbody.innerHTML = '';

    orders.pending.forEach(order => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${order.orderId}</td>
            <td>${order.customerName}</td>
            <td>${order.customerEmail}</td>
            <td>${order.product}</td>
            <td>${order.quantity}</td>
            <td>${order.category}</td>
            <td>${order.date}</td>
            <td>${order.weight}kg</td>
            <td>${order.shippingInstruction}</td>
            <td>${order.brandName}</td>
            <td>${order.packageDescription}</td>
            <td><button class="btn btn-sm btn-primary" onclick="shipOrder(${order.orderId})">Ship</button></td>
        `;
        tbody.appendChild(row);
    });
}

// Update shipped orders table
function updateShippedTable() {
    const tbody = document.querySelector('#shipped-orders tbody');
    tbody.innerHTML = '';

    orders.shipped.forEach(order => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${order.orderId}</td>
            <td>${order.customerName}</td>
            <td>${order.customerEmail}</td>
            <td>${order.product}</td>
            <td>${order.quantity}</td>
            <td>${order.category}</td>
            <td>${order.date}</td>
            <td>${order.weight}kg</td>
            <td>${order.shippingInstruction}</td>
        `;
        tbody.appendChild(row);
    });
}

// Load initial data (could be from localStorage)
function loadInitialData() {
    // Example of loading from localStorage:
    // const savedOrders = localStorage.getItem('orders');
    // if (savedOrders) {
    //     Object.assign(orders, JSON.parse(savedOrders));
    // }

    // For demo purposes, add some sample data
    if (orders.pending.length === 0 && orders.shipped.length === 0) {
        orders.pending.push({
            orderId: 1234,
            customerName: "Philimon",
            customerEmail: "pmwsiwale@gmail.com",
            product: "Laptop",
            quantity: "3",
            category: "Electronics",
            date: "2025-04-13",
            weight: "5",
            shippingInstruction: "Air",
            brandName: "HP",
            packageDescription: "Bubble-wrapped with charger"
        });

        orders.shipped.push({
            orderId: 2222,
            customerName: "Afiya Kaunda",
            customerEmail: "afiyakaunda@gmail.com",
            product: "iPhone",
            quantity: "1",
            category: "Electronics",
            date: "2025-01-01",
            weight: "1.5",
            shippingInstruction: "Sea",
            brandName: "Apple",
            packageDescription: "Original box with accessories"
        });
    }
}

// Make shipOrder available globally
window.shipOrder = shipOrder;