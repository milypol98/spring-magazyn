// settings.js – logika UI dla zakładki ustawień/profilu
(function () {

    // Formularz: walidacja oraz obsługa zmiany hasła
    const form = document.querySelector('form.profile-form');
    const changePassword = document.getElementById('changePassword');
    const passwordFields = document.getElementById('passwordFields');
    const newPassword = document.getElementById('newPassword');
    const confirmPassword = document.getElementById('confirmPassword');

    function togglePasswordFields() {
        if (!changePassword || !passwordFields) return;
        const enabled = changePassword.checked;
        passwordFields.classList.toggle('hidden', !enabled);
        if (newPassword && confirmPassword) {
            newPassword.disabled = !enabled;
            confirmPassword.disabled = !enabled;
            if (!enabled) {
                newPassword.value = '';
                confirmPassword.value = '';
                newPassword.classList.remove('invalid');
                confirmPassword.classList.remove('invalid');
            }
        }
    }

    changePassword?.addEventListener('change', togglePasswordFields);
    togglePasswordFields();

    if (form) {
        form.addEventListener('submit', (e) => {
            // Wymagane: firstname, lastName, email
            const required = Array.from(form.querySelectorAll('[required]'));
            let valid = true;
            required.forEach(el => {
                const v = (el.value || '').trim();
                if (!v || (el.type === 'email' && !/^\S+@\S+\.\S+$/.test(v))) {
                    el.classList.add('invalid');
                    valid = false;
                } else {
                    el.classList.remove('invalid');
                }
            });

            // Jeśli zmieniamy hasło: sprawdź długość i zgodność
            if (changePassword?.checked) {
                const pw = (newPassword?.value || '').trim();
                const pw2 = (confirmPassword?.value || '').trim();
                if (pw.length < 8) {
                    newPassword?.classList.add('invalid');
                    valid = false;
                } else {
                    newPassword?.classList.remove('invalid');
                }
                if (pw !== pw2) {
                    confirmPassword?.classList.add('invalid');
                    valid = false;
                } else {
                    confirmPassword?.classList.remove('invalid');
                }
            }

            if (!valid) {
                e.preventDefault();
                (form.querySelector('.invalid') || form).focus();
            }
        });
    }
})();