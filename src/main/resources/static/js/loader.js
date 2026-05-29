document.addEventListener("DOMContentLoaded", function() {
    const loader = document.createElement('div');
    loader.id = 'loader-wrapper';
    loader.innerHTML = `
        <img src="/images/Logo.png" class="loader-logo" alt="Loading...">
        <div class="spinner"></div>
    `;
    document.body.appendChild(loader);

    const links = document.querySelectorAll('a');
    links.forEach(link => {
        link.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href && href !== '#' && !href.startsWith('javascript:')) {
                e.preventDefault();
                loader.style.display = 'flex';
                loader.style.opacity = '1';
                setTimeout(() => {
                    window.location.href = href;
                }, 1500);
            }
        });
    });

    window.addEventListener('pageshow', function(event) {
        if (event.persisted) {
            loader.style.display = 'none';
            loader.style.opacity = '0';
        }
    });

    // Hide loader after initial load
    setTimeout(function() {
        loader.style.opacity = '0';
        setTimeout(function() {
            loader.style.display = 'none';
        }, 500);
    }, 100);
});