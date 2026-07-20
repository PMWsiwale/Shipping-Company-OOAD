// DOM Elements
const logoutBtn = document.getElementById('logoutBtn');
const verifyOrderBtn = document.getElementById('verifyOrderBtn');
const reportDiscrepancyBtn = document.getElementById('reportDiscrepancyBtn');
const cancelReportBtn = document.getElementById('cancelReportBtn');
const discrepancyModal = document.getElementById('discrepancyModal');
const closeModalBtn = document.querySelector('.close-btn');
const discrepancyForm = document.getElementById('discrepancyForm');
const shipmentSearch = document.getElementById('shipmentSearch');
const statusFilter = document.getElementById('statusFilter');
const currentUserElement = document.getElementById('currentUser');
const verifyShipmentSearch = document.getElementById('verifyShipmentSearch');
const searchVerifyBtn = document.getElementById('searchVerifyBtn');

// Sample Data
const shipmentsData = [{
        id: 'SH-1001',
        status: 'processing',
        supplier: 'Tech Supplies Inc.',
        items: 3,
        expectedDate: '2023-11-17',
        details: {
            itemName: 'Electronics Kit',
            quantity: 10,
            weight: '100kg',
            package: 'Large Crate',
            stockType: 'Perishable',
            category: 'Electronics',
            customerId: 'CUST-001',
            purchaseDate: '2023-11-15'
        }
    },
    {
        id: 'SH-1002',
        status: 'received',
        supplier: 'Office Essentials',
        items: 5,
        expectedDate: '2023-11-15',
        details: {
            itemName: 'Office Chairs',
            quantity: 15,
            weight: '250kg',
            package: 'Pallets',
            stockType: 'Non-Perishable',
            category: 'Furniture',
            customerId: 'CUST-042',
            purchaseDate: '2023-11-16'
        }
    }
];

// Initialize the dashboard
document.addEventListener('DOMContentLoaded', function() {
    // Load user data
    const currentUser = localStorage.getItem('currentUser') || 'Alex Jr';
    currentUserElement.textContent = currentUser;

    // Set last login time
    document.getElementById('lastLogin').textContent = getFormattedTime();

    // Render shipments table
    renderShipmentsTable(shipmentsData);

    // Set up event listeners
    setupEventListeners();
});

// Event Listeners Setup
function setupEventListeners() {
    // Logout button
    logoutBtn.addEventListener('click', handleLogout);

    // Verify order button
    verifyOrderBtn.addEventListener('click', verifyOrder);

    // Discrepancy report buttons
    reportDiscrepancyBtn.addEventListener('click', openDiscrepancyModal);
    cancelReportBtn.addEventListener('click', closeDiscrepancyModal);
    closeModalBtn.addEventListener('click', closeDiscrepancyModal);

    // Modal close when clicking outside
    window.addEventListener('click', function(event) {
        if (event.target === discrepancyModal) {
            closeDiscrepancyModal();
        }
    });

    // Form submission
    discrepancyForm.addEventListener('submit', submitDiscrepancyReport);

    // Search functionality
    shipmentSearch.addEventListener('input', filterShipments);
    searchVerifyBtn.addEventListener('click', filterVerifyShipments);
    verifyShipmentSearch.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            filterVerifyShipments();
        }
    });

    // Status filter
    statusFilter.addEventListener('change', filterShipments);
}

// Filter Verify Shipments Table
function filterVerifyShipments() {
    const searchTerm = verifyShipmentSearch.value.toLowerCase();
    const rows = document.querySelectorAll('#shipmentVerify tr');

    rows.forEach(row => {
        const cells = row.querySelectorAll('td');
        let matches = false;

        cells.forEach(cell => {
            if (cell.textContent.toLowerCase().includes(searchTerm)) {
                matches = true;
            }
        });

        row.style.display = matches ? '' : 'none';
    });
}

// Render Shipments Table
function renderShipmentsTable(shipments) {
    const tbody = document.getElementById('shipmentTableBody');
    tbody.innerHTML = '';

    shipments.forEach(shipment => {
                const row = document.createElement('tr');

                // Status badge class
                const badgeClass = `badge-${shipment.status}`;

                row.innerHTML = `
            <td>${shipment.id}</td>
            <td><span class="badge ${badgeClass}">${capitalizeFirstLetter(shipment.status)}</span></td>
            <td>${shipment.supplier}</td>
            <td>${shipment.items} items</td>
            <td>${shipment.expectedDate}</td>
            <td class="actions">
                <button class="btn-icon" title="View Details"><i class="fas fa-eye"></i></button>
                ${shipment.status === 'processing' ? 
                    `<button class="btn-icon receive-btn" title="Receive" data-id="${shipment.id}"><i class="fas fa-check"></i></button>` : 
                    `<button class="btn-icon" title="Mark as Problem"><i class="fas fa-exclamation-triangle"></i></button>`}
            </td>
        `;
        
        tbody.appendChild(row);
    });
    
    // Add event listeners to action buttons
    document.querySelectorAll('.receive-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const shipmentId = this.getAttribute('data-id');
            receiveShipment(shipmentId);
        });
    });
}

// Filter Shipments (for the Current Incoming Shipments table)
function filterShipments() {
    const searchTerm = shipmentSearch.value.toLowerCase();
    const statusFilterValue = statusFilter.value;
    
    const filtered = shipmentsData.filter(shipment => {
        const matchesSearch = 
            shipment.id.toLowerCase().includes(searchTerm) ||
            shipment.supplier.toLowerCase().includes(searchTerm) ||
            shipment.details.itemName.toLowerCase().includes(searchTerm);
        
        const matchesStatus = 
            statusFilterValue === 'all' || 
            shipment.status === statusFilterValue;
        
        return matchesSearch && matchesStatus;
    });
    
    renderShipmentsTable(filtered);
}

// Verify Order
function verifyOrder() {
    // In a real app, you would get the selected shipment
    const selectedShipment = shipmentsData[0]; // Example: first shipment
    
    // Update status to 'received'
    selectedShipment.status = 'received';
    
    // Show success message
    showNotification('Order verified successfully!', 'success');
    
    // Refresh the table
    renderShipmentsTable(shipmentsData);
    
    // Update counters
    updateCounters();
}

// Receive Shipment
function receiveShipment(shipmentId) {
    const shipment = shipmentsData.find(s => s.id === shipmentId);
    if (shipment) {
        shipment.status = 'received';
        showNotification(`Shipment ${shipmentId} marked as received`, 'success');
        renderShipmentsTable(shipmentsData);
        updateCounters();
    }
}

// Discrepancy Report Functions
function openDiscrepancyModal() {
    discrepancyModal.style.display = 'flex';
    document.body.style.overflow = 'hidden';
}

function closeDiscrepancyModal() {
    discrepancyModal.style.display = 'none';
    document.body.style.overflow = 'auto';
    discrepancyForm.reset();
}

function submitDiscrepancyReport(e) {
    e.preventDefault();
    
    const type = document.getElementById('discrepancyType').value;
    const details = document.getElementById('discrepancyDetails').value;
    const photos = document.getElementById('discrepancyPhotos').files;
    
    // In a real app, you would send this to a server
    console.log('Discrepancy Report:', { type, details, photos });
    
    showNotification('Discrepancy report submitted successfully!', 'success');
    closeDiscrepancyModal();
}

// Helper Functions
function getFormattedTime() {
    const now = new Date();
    const options = { 
        weekday: 'long', 
        year: 'numeric', 
        month: 'short', 
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    };
    return now.toLocaleDateString('en-US', options);
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function showNotification(message, type) {
    // Create notification element
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.textContent = message;
    
    // Add to body
    document.body.appendChild(notification);
    
    // Remove after 3 seconds
    setTimeout(() => {
        notification.classList.add('fade-out');
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

function updateCounters() {
    const pendingCount = shipmentsData.filter(s => s.status === 'processing').length;
    const receivedCount = shipmentsData.filter(s => s.status === 'received').length;
    
    document.getElementById('pendingCount').textContent = pendingCount;
    document.getElementById('receivedCount').textContent = receivedCount;
}

function handleLogout() {
    // In a real app, you would clear the session
    localStorage.removeItem('currentUser');
    showNotification('Logged out successfully', 'success');
    
    // Redirect to login page
    setTimeout(() => {
        window.location.href = 'login.html'; // Change to your login page
    }, 1000);
}

// Initialize counters
updateCounters();