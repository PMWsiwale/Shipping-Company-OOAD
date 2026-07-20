  const orderListView = document.getElementById("orderListView");
    const orderDetailsView = document.getElementById("orderDetailsView");
    const ordersTableBody = document.getElementById("ordersTableBody");
    const orderDetailsTableBody = document.getElementById("orderDetailsTableBody");
    // Toggle view buttons
    document.getElementById("showListBtn").addEventListener("click", function() {
        orderListView.style.display = "block";
        orderDetailsView.style.display = "none";
        this.classList.add("active");
        document.getElementById("showDetailsBtn").classList.remove("active");
    });

    document.getElementById("showDetailsBtn").addEventListener("click", function() {
        orderListView.style.display = "none";
        orderDetailsView.style.display = "block";
        this.classList.add("active");
        document.getElementById("showListBtn").classList.remove("active");
    });