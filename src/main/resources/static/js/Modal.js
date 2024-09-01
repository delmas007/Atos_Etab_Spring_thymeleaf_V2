document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById("confirmationModal");
    const closeBtn = document.querySelector(".close");
    const confirmYesBtn = document.getElementById("confirmYes");
    const confirmNoBtn = document.getElementById("confirmNo");
    let targetForm;

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            targetForm = button.closest('form'); // Récupérer le formulaire associé
            modal.style.display = "block";
        });
    });

    closeBtn.addEventListener('click', () => {
        modal.style.display = "none";
    });

    confirmNoBtn.addEventListener('click', () => {
        modal.style.display = "none";
    });

    confirmYesBtn.addEventListener('click', () => {
        if (targetForm) {
            targetForm.submit(); // Soumettre le formulaire après confirmation
        }
        modal.style.display = "none";
    });

    window.addEventListener('click', (event) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });
});
