// DOM Elements
const tabs = document.querySelectorAll('.tab');
const tabContents = document.querySelectorAll('.tab-content');
const orderDetailsModal = document.getElementById('orderDetailsModal');
const modalOrderId = document.getElementById('modalOrderId');
const customerName = document.getElementById('customerName');
const orderDate = document.getElementById('orderDate');
const shippingAddress = document.getElementById('shippingAddress');
const orderItems = document.getElementById('orderItems');
const searchInputs = document.querySelectorAll('.search-box input');
const timeFilter = document.querySelector('.time-filter select');

// Sample data for orders
const ordersData = {
    'ORD-1001': {
        customer: 'Alex ',
        date: '2023-11-15',
        priority: 'Medium',
        status: 'Pending',
        address: '123 Main St, Anytown, CA 90210',
        items: [
            { name: 'Wireless Headphones', sku: 'SKU-78901', quantity: 1, location: 'Aisle 3, Shelf B' },
            { name: 'USB-C Cable', sku: 'SKU-34567', quantity: 2, location: 'Aisle 1, Shelf D' }
        ]
    },
    'ORD-1002': {
        customer: 'Bwembya',
        date: '2023-11-14',
        priority: 'High',
        status: 'Pending',
        address: '456 Oak Ave, Somewhere, NY 10001',
        items: [
            { name: 'Smartphone', sku: 'SKU-12345', quantity: 1, location: 'Aisle 5, Shelf A' },
            { name: 'Phone Case', sku: 'SKU-67890', quantity: 1, location: 'Aisle 5, Shelf C' },
            { name: 'Screen Protector', sku: 'SKU-24680', quantity: 2, location: 'Aisle 5, Shelf C' },
            { name: 'Wireless Charger', sku: 'SKU-13579', quantity: 1, location: 'Aisle 4, Shelf B' }
        ]
    },
    'ORD-1003': {
        customer: 'Faith',
        date: '2023-11-16',
        priority: 'Low',
        status: 'Pending',
        address: '789 Pine Rd, Nowhere, TX 75001',
        items: [
            { name: 'Laptop', sku: 'SKU-11223', quantity: 1, location: 'Aisle 2, Shelf A' },
            { name: 'Laptop Bag', sku: 'SKU-44556', quantity: 1, location: 'Aisle 2, Shelf C' }
        ]
    },
    'ORD-0998': {
        customer: 'Emily Davis',
        date: '2023-11-13',
        priority: 'Medium',
        status: 'Completed',
        address: '321 Elm St, Anywhere, FL 32003',
        items: [
            { name: 'Bluetooth Speaker', sku: 'SKU-33445', quantity: 1, location: 'Aisle 3, Shelf A' },
            { name: 'AA Batteries', sku: 'SKU-66778', quantity: 4, location: 'Aisle 1, Shelf A' },
            { name: 'HDMI Cable', sku: 'SKU-99112', quantity: 2, location: 'Aisle 1, Shelf D' }
        ],
        fulfilledBy: 'John Doe',
        fulfilledDate: '2023-11-13'
    },
    'ORD-0997': {
        customer: 'Robert Wilson',
        date: '2023-11-12',
        priority: 'Low',
        status: 'Completed',
        address: '654 Maple Dr, Somewhere, WA 98001',
        items: [
            { name: 'Smart Watch', sku: 'SKU-22334', quantity: 1, location: 'Aisle 4, Shelf A' }
        ],
        fulfilledBy: 'Jane Smith',
        fulfilledDate: '2023-11-12'
    }
};

// Initialize the dashboard
document.addEventListener('DOMContentLoaded', function() {
    // Set up tab switching
    tabs.forEach(tab => {
        tab.addEventListener('click', switchTab);
    });

    // Set up search functionality
    searchInputs.forEach(input => {
        input.addEventListener('input', handleSearch);
    });

    // Set up time filter
    if (timeFilter) {
        timeFilter.addEventListener('change', updateMetrics);
    }

    // Initialize metrics
    updateMetrics();
});

// Switch between tabs
function switchTab(e) {
    const tabName = this.textContent.includes('Pending') ? 'pending' : 'history';

    // Hide all tab contents
    tabContents.forEach(tab => {
        tab.classList.remove('active');
    });

    // Deactivate all tabs
    tabs.forEach(tab => {
        tab.classList.remove('active');
    });

    // Activate selected tab
    document.getElementById(`${tabName}-tab`).classList.add('active');
    this.classList.add('active');
}

// View order details
function viewOrderDetails(orderId) {
    const order = ordersData[orderId];
    if (!order) return;

    // Update modal content
    modalOrderId.textContent = orderId;
    customerName.textContent = order.customer;
    orderDate.textContent = order.date;
    shippingAddress.textContent = order.address;

    // Clear previous items
    orderItems.innerHTML = '';

    // Add items to the table
    order.items.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.name}</td>
            <td>${item.sku}</td>
            <td>${item.quantity}</td>
            <td>${item.location}</td>
        `;
        orderItems.appendChild(row);
    });

    // Show the modal
    orderDetailsModal.style.display = 'block';
}

// Close the modal
function closeModal() {
    orderDetailsModal.style.display = 'none';
}

// Mark order as fulfilled
function markAsFulfilled() {
    const orderId = modalOrderId.textContent;
    const order = ordersData[orderId];

    if (order) {
        order.status = 'Completed';
        order.fulfilledBy = 'John Doe'; // Current user
        order.fulfilledDate = new Date().toISOString().split('T')[0]; // Today's date

        // In a real app, we would send this update to the server
        alert(`Order ${orderId} has been marked as fulfilled!`);

        // Refresh the UI
        closeModal();
        switchTab({ target: document.querySelector('.tab.active') });
    }
}

// Print packing slip
function printPackingSlip() {
    const orderId = modalOrderId.textContent;
    const order = ordersData[orderId];

    if (order) {
        // In a real app, this would generate a printable packing slip
        const printWindow = window.open('', '_blank');
        printWindow.document.write(`
            <html>
                <head>
                    <title>Packing Slip - ${orderId}</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 20px; }
                        h1 { color: #333; }
                        .info { margin-bottom: 20px; }
                        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
                        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                        th { background-color: #f2f2f2; }
                    </style>
                </head>
                <body>
                    <h1>Packing Slip</h1>
                    <div class="info">
                        <p><strong>Order ID:</strong> ${orderId}</p>
                        <p><strong>Customer:</strong> ${order.customer}</p>
                        <p><strong>Shipping Address:</strong> ${order.address}</p>
                        <p><strong>Date:</strong> ${new Date().toLocaleDateString()}</p>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th>Item</th>
                                <th>SKU</th>
                                <th>Quantity</th>
                                <th>Location</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${order.items.map(item => `
                                <tr>
                                    <td>${item.name}</td>
                                    <td>${item.sku}</td>
                                    <td>${item.quantity}</td>
                                    <td>${item.location}</td>
                                </tr>
                            `).join('')}
                        </tbody>
                    </table>
                </body>
            </html>
        `);
        printWindow.document.close();
        printWindow.print();
    }
}

// Handle search functionality
function handleSearch(e) {
    const searchTerm = e.target.value.toLowerCase();
    const table = e.target.closest('.section').querySelector('table');
    
    if (!table) return;
    
    const rows = table.querySelectorAll('tbody tr');
    
    rows.forEach(row => {
        const text = row.textContent.toLowerCase();
        row.style.display = text.includes(searchTerm) ? '' : 'none';
    });
}

// Update performance metrics
function updateMetrics() {
    const period = timeFilter ? timeFilter.value : 'Last 90 days';
    let metrics;
    
    // In a real app, we would fetch these metrics from an API based on the selected period
    switch (period) {
        case 'Last 7 days':
            metrics = {
                ordersFulfilled: 12,
                onTimeRate: '94%',
                avgItemsPerOrder: 2.1,
                avgFulfillmentTime: '13.2 min',
                ordersTrend: 'down',
                onTimeTrend: 'up',
                itemsTrend: 'down',
                timeTrend: 'up'
            };
            break;
        case 'Last 30 days':
            metrics = {
                ordersFulfilled: 32,
                onTimeRate: '95%',
                avgItemsPerOrder: 2.3,
                avgFulfillmentTime: '12.8 min',
                ordersTrend: 'up',
                onTimeTrend: 'up',
                itemsTrend: 'down',
                timeTrend: 'down'
            };
            break;
        case 'Last 90 days':
        default:
            metrics = {
                ordersFulfilled: 48,
                onTimeRate: '96%',
                avgItemsPerOrder: 2.4,
                avgFulfillmentTime: '12.5 min',
                ordersTrend: 'up',
                onTimeTrend: 'up',
                itemsTrend: 'down',
                timeTrend: 'down'
            };
    }
    
    // Update the metrics cards
    const metricCards = document.querySelectorAll('.metric-card');
    if (metricCards.length >= 4) {
        // Orders Fulfilled
        metricCards[0].querySelector('.metric-value').textContent = metrics.ordersFulfilled;
        const ordersTrend = metricCards[0].querySelector('.metric-trend');
        ordersTrend.className = `metric-trend ${metrics.ordersTrend}`;
        ordersTrend.innerHTML = `<i class="fas fa-arrow-${metrics.ordersTrend}"></i> ${metrics.ordersTrend === 'up' ? '+' : '-'}${metrics.ordersTrend === 'up' ? '5' : '2'}% from last period`;
        
        // On-Time Rate
        metricCards[1].querySelector('.metric-value').textContent = metrics.onTimeRate;
        const onTimeTrend = metricCards[1].querySelector('.metric-trend');
        onTimeTrend.className = `metric-trend ${metrics.onTimeTrend}`;
        onTimeTrend.innerHTML = `<i class="fas fa-arrow-${metrics.onTimeTrend}"></i> ${metrics.onTimeTrend === 'up' ? '+' : '-'}${metrics.onTimeTrend === 'up' ? '3' : '1'}% from last period`;
        
        // Avg Items/Order
        metricCards[2].querySelector('.metric-value').textContent = metrics.avgItemsPerOrder;
        const itemsTrend = metricCards[2].querySelector('.metric-trend');
        itemsTrend.className = `metric-trend ${metrics.itemsTrend}`;
        itemsTrend.innerHTML = `<i class="fas fa-arrow-${metrics.itemsTrend}"></i> ${metrics.itemsTrend === 'up' ? '+' : '-'}0.${metrics.itemsTrend === 'up' ? '3' : '2'} from last period`;
        
        // Avg Fulfillment Time
        metricCards[3].querySelector('.metric-value').textContent = metrics.avgFulfillmentTime;
        const timeTrend = metricCards[3].querySelector('.metric-trend');
        timeTrend.className = `metric-trend ${metrics.timeTrend}`;
        timeTrend.innerHTML = `<i class="fas fa-arrow-${metrics.timeTrend}"></i> ${metrics.timeTrend === 'up' ? '+' : '-'}${metrics.timeTrend === 'up' ? '1.5' : '1.3'} min from last period`;
    }
}

// Close modal when clicking outside of it
window.addEventListener('click', function(e) {
    if (e.target === orderDetailsModal) {
        closeModal();
    }
});