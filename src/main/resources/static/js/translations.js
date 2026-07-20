const translations = {
  en: {
    welcome_heading: "WELCOME TO FRAGIP LOGISTICS",
    welcome_subheading: "Efficient Shipping & Supply Chain Services in Kitwe, Zambia",
    login_button: "Login",
    services_heading: "Our Services",
    services_description: "We provide top-notch logistics, freight forwarding, and supply chain solutions across Zambia and beyond.",
    contact_heading: "Contact Us",
    contact_subheading: "We are based in Kitwe, Zambia. Reach out to us directly or leave a message below:",
    office_heading: "Kitwe Office",
    send_message: "Send Message",
    your_name: "Your Name",
    your_email: "Your Email",
    message_label: "Message",
    note: "Note: This form opens your default email app to send the message.",
    nav_home: "Home",
    nav_services: "Services",
    nav_contact: "Contact"
  },
  bemba: {
    welcome_heading: "TWAMIPOKELELA KU FRAGIP LOGISTICS",
    welcome_subheading: "Ukutumina bwino nokupeela insambu sha Logistics ku Kitwe, Zambia",
    login_button: "Injika",
    services_heading: "Imilimo Yesu",
    services_description: "Tulepeela utumishi ubusuma bwa logistics, freight forwarding, no kusansamusha impiya mu Zambia yonse.",
    contact_heading: "Ukwakututumina",
    contact_subheading: "Tuli ku Kitwe, Zambia. Tutumine pano panshi capamo:",
    office_heading: "Ofisi ya pa Kitwe",
    send_message: "Tuma Ubutumwa",
    your_name: "Ishina lyobe",
    your_email: "Imeyilo lyobe",
    message_label: "Ubutumwa",
    note: "Cisuma: Ifomu ili litumina email lya mu foni yobe.",
    nav_home: "Ikula",
    nav_services: "Imilimo",
    nav_contact: "Ukwakututumina"
  },
  nyanja: {
    welcome_heading: "TIKUWA MWA FRAGIP LOGISTICS",
    welcome_subheading: "Kutumiza ndi kasamalidwe ka katundu mu Kitwe, Zambia",
    login_button: "Lowani",
    services_heading: "Ntchito Zathu",
    services_description: "Timapereka ntchito zabwino za logistics, freight forwarding, ndi kasamalidwe ka zinthu ku Zambia ndi kunja.",
    contact_heading: "Lumikizanani Nafe",
    contact_subheading: "Tili ku Kitwe, Zambia. Lumikizanani nafe kapena siyanani uthenga pansipa:",
    office_heading: "Ofesi ya ku Kitwe",
    send_message: "Tumizani Uthenga",
    your_name: "Dzina Lanu",
    your_email: "Imelo Yanu",
    message_label: "Uthenga",
    note: "Zindikirani: Fomu iyi imatsegula pulogalamu yanu ya email kuti mutumize uthenga.",
    nav_home: "Pakhomo",
    nav_services: "Ntchito",
    nav_contact: "Lumikizanani"
  }
};

function applyTranslations(lang) {
  const elements = document.querySelectorAll('[data-i18n]');
  elements.forEach(el => {
    const key = el.getAttribute('data-i18n');
    if (translations[lang] && translations[lang][key]) {
      el.textContent = translations[lang][key];
    }
  });
}

const langSelect = document.getElementById('languageSelect');
const savedLang = localStorage.getItem('selectedLanguage') || 'en';
langSelect.value = savedLang;
applyTranslations(savedLang);

langSelect.addEventListener('change', () => {
  const selectedLang = langSelect.value;
  localStorage.setItem('selectedLanguage', selectedLang);
  applyTranslations(selectedLang);
});

const roleForms = {
  Admin: document.getElementById("adminForm"),
  Supplier: document.getElementById("supplierForm"),
  User: document.getElementById("userForm")
};

document.querySelectorAll('[data-bs-target="#loginModal"]').forEach(link => {
  link.addEventListener('click', function () {
    const role = this.getAttribute('data-role') || 'User';
    document.getElementById('loginModalLabel').textContent = `${role} Login`;

    // Hide all forms first
    document.querySelectorAll(".role-form").forEach(f => f.style.display = "none");

    // Show only the selected role's form
    if (roleForms[role]) {
      roleForms[role].style.display = "block";
    } else {
      roleForms["User"].style.display = "block"; // fallback
    }
  });
});

