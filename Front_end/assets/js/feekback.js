document.addEventListener("DOMContentLoaded", function () {
    const feedbackForm = document.getElementById("feedbackForm");
    const feedbackTable = document.getElementById("feedbackTable").getElementsByTagName('tbody')[0];
    
    function loadFeedbacks() {
      const feedbacks = JSON.parse(localStorage.getItem("feedbacks")) || [];
      feedbackTable.innerHTML = "";
      feedbacks.forEach((feedback, index) => {
        addFeedbackToTable(feedback, index);
      });
    }
    
    function addFeedbackToTable(feedback, index) {
      let newRow = feedbackTable.insertRow();
      newRow.insertCell(0).innerText = feedback.name;
      newRow.insertCell(1).innerText = feedback.email;
      newRow.insertCell(2).innerText = feedback.feedback;
      newRow.insertCell(3).innerText = feedback.rating ? feedback.rating + " ★" : "Chưa đánh giá";
      let deleteCell = newRow.insertCell(4);
      let deleteButton = document.createElement("button");
      deleteButton.innerText = "Xóa";
      deleteButton.className = "delete-btn";
      deleteButton.addEventListener("click", function () {
        deleteFeedback(index);
      });
      deleteCell.appendChild(deleteButton);
    }
    
    function deleteFeedback(index) {
      let feedbacks = JSON.parse(localStorage.getItem("feedbacks")) || [];
      feedbacks.splice(index, 1);
      localStorage.setItem("feedbacks", JSON.stringify(feedbacks));
      loadFeedbacks();
    }
    
    feedbackForm.addEventListener("submit", function (event) {
      event.preventDefault();
      
      let feedbackData = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        feedback: document.getElementById("feedback").value,
        rating: document.querySelector("input[name='rating']:checked")?.value || ""
      };
      
      let feedbacks = JSON.parse(localStorage.getItem("feedbacks")) || [];
      feedbacks.push(feedbackData);
      localStorage.setItem("feedbacks", JSON.stringify(feedbacks));
      
      loadFeedbacks();
      feedbackForm.reset();
    });
    
    loadFeedbacks();
  });

  document.getElementById("feedback").addEventListener("input", function() {
    if (this.value.length > 200) {
      this.value = this.value.substring(0, 200);
    }
  });

  document.getElementById("feedbackForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    let notification = document.getElementById("notification");
    notification.style.display = "block";
    
    setTimeout(() => {
        notification.style.opacity = "0"; // Làm mờ dần
        setTimeout(() => {
          notification.style.display = "none"; // Ẩn hẳn sau khi mờ
        }, 1000); // Đợi hiệu ứng hoàn tất (1s)
      }, 2000);
  });