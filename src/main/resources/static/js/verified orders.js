// DOM Elements
const logoutBtn = document.getElementById('logoutBtn');
const dateFilter = document.getElementById('dateFilter');
const customDateRange = document.getElementById('customDateRange');
const startDate = document.getElementById('startDate');
const endDate = document.getElementById('endDate');
const applyFiltersBtn = document.getElementById('applyFilters');
const exportDataBtn = document.getElementById('exportData');
const currentUserElement = document.getElementById('currentUser');

// Sample Data
//const verifiedOrders = [{
        //id: 'ORD-1001',
        //customer: 'Alice Smith',
        //items: 5,
        //verifiedBy: 'Alex Jr',
        //dateVerified: '2023-11-15 10:30 AM',
        //status: 'verified',
        //details: {
         //   items: [
        //        { name: 'Electronics Kit', quantity: 2 },
       //         { name: 'Office Chair', quantity: 3 }
      //      ]
     //   }
    //},
   // {
        //id: 'ORD-1002',
        //customer: 'Bob Johnson',
        //items: 3,
      //  verifiedBy: 'Sarah Smith',
    //    dateVerified: '2023-11-14 02:15 PM',
    //    status: 'verified',
  //      details: {
            //items: [
          //      { name: 'Printer', quantity: 1 },
        //        { name: 'Ink Cartridges', quantity: 2 }
      //      ]
    //    }
    //},
    //{
        //id: 'ORD-1003',
        //customer: 'Charlie Brown',
        //items: 8,
        //verifiedBy: 'Mike Johnson',
        //dateVerified: '2023-11-13 09:45 AM',
        //status: 'verified',
       // details: {
           // items: [
                { name: 'Desk', quantity: 2 },
          //      { name: 'Monitor', quantity: 3 },
        //        { name: 'Keyboard', quantity: 3 }
      //      ]
    //    }
  //  }
//];

// Initialize the page
document.addEventListener('DOMContentLoaded', function() {
    // Load user data
    const currentUser = localStorage.getItem('currentUser') || 'Alex Jr';
    currentUserElement.textContent = currentUser;

    // Set up event listeners
    setupEventListeners();

    // Render the initial table
    renderVerifiedOrders(verifiedOrders);
});

// Event Listeners Setup
function setupEventListeners() {
    // Logout button
    logoutBtn.addEventListener('click', handleLogout);

    // Date filter change
    dateFilter.addEventListener('change', function() {
        if (this.value === 'custom') {
            customDateRange.style.display = 'flex';
        } else {
            customDateRange.style.display = 'none';
        }
    });

    // Apply filters button
    applyFiltersBtn.addEventListener('click', applyFilters);

    // Export data button
    exportDataBtn.addEventListener('click', exportData);

    // Set default dates for custom range
    const today = new Date();
    const oneWeekAgo = new Date();
    oneWeekAgo.setDate(today.getDate() - 7);

    startDate.valueAsDate = oneWeekAgo;
    endDate.valueAsDate = today;
}

// Render Verified Orders Table
function renderVerifiedOrders(orders) {
    const tbody = document.querySelector('.data-table tbody');
    tbody.innerHTML = '';

    orders.forEach(order => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${order.id}</td>
            <td>${order.customer}</td>
            <td>${order.items} items</td>
            <td>${order.verifiedBy}</td>
            <td>${order.dateVerified}</td>
            <td><span class="badge badge-success">${capitalizeFirstLetter(order.status)}</span></td>
            <td class="actions">
                <button class="btn-icon view-details-btn" title="View Details" data-id="${order.id}">
                    <i class="fas fa-eye"></i>
                </button>
            </td>
        `;

        tbody.appendChild(row);
    });

    // Add event listeners to view details buttons
    document.querySelectorAll('.view-details-btn').forEach(btn => {
        btn.addEventListener('click', function() {
            const orderId = this.getAttribute('data-id');
            viewOrderDetails(orderId);
        });
    });
}

// Apply Filters
function applyFilters() {
    const filterValue = dateFilter.value;
    let filteredOrders = [...verifiedOrders];

    if (filterValue === 'today') {
        const today = new Date().toISOString().split('T')[0];
        filteredOrders = filteredOrders.filter(order => {
            const orderDate = order.dateVerified.split(' ')[0];
            return orderDate === today;
        });
    } else if (filterValue === 'week') {
        const oneWeekAgo = new Date();
        oneWeekAgo.setDate(oneWeekAgo.getDate() - 7);

        filteredOrders = filteredOrders.filter(order => {
            const orderDate = new Date(order.dateVerified.split(' ')[0]);
            return orderDate >= oneWeekAgo;
        });
    } else if (filterValue === 'month') {
        const oneMonthAgo = new Date();
        oneMonthAgo.setMonth(oneMonthAgo.getMonth() - 1);

        filteredOrders = filteredOrders.filter(order => {
            const orderDate = new Date(order.dateVerified.split(' ')[0]);
            return orderDate >= oneMonthAgo;
        });
    } else if (filterValue === 'custom') {
        const start = new Date(startDate.value);
        const end = new Date(endDate.value);

        filteredOrders = filteredOrders.filter(order => {
            const orderDate = new Date(order.dateVerified.split(' ')[0]);
            return orderDate >= start && orderDate <= end;
        });
    }

    renderVerifiedOrders(filteredOrders);
    showNotification(`Applied filters: ${filterValue}`, 'success');
}

// View Order Details
function viewOrderDetails(orderId) {
    const order = verifiedOrders.find(o => o.id === orderId);
    if (!order) return;

    // Create modal content
    const modalContent = `
        <div class="modal-content">
            <span class="close-btn">&times;</span>
            <h2><i class="fas fa-info-circle"></i> Order Details - ${order.id}</h2>
            
            <div class="order-details">
                <div class="detail-row">
                    <span class="detail-label">Customer:</span>
                    <span class="detail-value">${order.customer}</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">Verified By:</span>
                    <span class="detail-value">${order.verifiedBy}</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">Date Verified:</span>
                    <span class="detail-value">${order.dateVerified}</span>
                </div>
                
                <h3>Items</h3>
                <table class="items-table">
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${order.details.items.map(item => `
                            <tr>
                                <td>${item.name}</td>
                                <td>${item.quantity}</td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        </div>
    `;
    
    // Create and show modal
    const modal = document.createElement('div');
    modal.className = 'modal';
    modal.id = 'orderDetailsModal';
    modal.innerHTML = modalContent;
    document.body.appendChild(modal);
    
    // Show modal
    modal.style.display = 'flex';
    document.body.style.overflow = 'hidden';
    
    // Close modal handlers
    const closeBtn = modal.querySelector('.close-btn');
    closeBtn.addEventListener('click', () => {
        modal.remove();
        document.body.style.overflow = 'auto';
    });
    
    window.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.remove();
            document.body.style.overflow = 'auto';
        }
    });
}

// Export Data
function exportData() {
    // In a real app, this would export to CSV/Excel
    showNotification('Export functionality would save data as CSV/Excel', 'info');
    console.log('Exporting data:', verifiedOrders);
    
    // Simple CSV export example
    const headers = ['Order ID', 'Customer', 'Items', 'Verified By', 'Date Verified', 'Status'];
    const csvContent = [
        headers.join(','),
        ...verifiedOrders.map(order => 
            [order.id, order.customer, order.items, order.verifiedBy, order.dateVerified, order.status].join(',')
        )
    ].join('\n');
    
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.setAttribute('href', url);
    link.setAttribute('download', `verified_orders_${new Date().toISOString().split('T')[0]}.csv`);
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

// Helper Functions
function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function showNotification(message, type) {
    // Create notification element
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.innerHTML = `
        <i class="fas ${type === 'success' ? 'fa-check-circle' : type === 'error' ? 'fa-exclamation-circle' : 'fa-info-circle'}"></i>
        ${message}
    `;
    
    // Add to body
    document.body.appendChild(notification);
    
    // Remove after 3 seconds
    setTimeout(() => {
        notification.classList.add('fade-out');
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

function handleLogout() {
    localStorage.removeItem('currentUser');
    showNotification('Logged out successfully', 'success');
    
    // Redirect to login page
    setTimeout(() => {
        window.location.href = 'login.html';
    }, 1000);
}