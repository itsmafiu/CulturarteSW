document.addEventListener("DOMContentLoaded", () => {
  const input = document.getElementById("buscar");
  const lista = document.getElementById("resultados");
  let timeout;
  let selectedIndex = -1;

  input.addEventListener("input", () => {
    clearTimeout(timeout);
    const query = input.value.trim();

    if (query === "") {
      lista.classList.remove("show");
      lista.innerHTML = "";
      return;
    }

    timeout = setTimeout(() => buscar(query), 100);
  });

  input.addEventListener("keydown", (e) => {
    const items = lista.querySelectorAll(".dropdown-item");

    if (e.key === "ArrowDown") {
      e.preventDefault();
      if (items.length > 0) {
        selectedIndex = (selectedIndex + 1) % items.length;
        actualizarSeleccion(items);
      }
    } else if (e.key === "ArrowUp") {
      e.preventDefault();
      if (items.length > 0) {
        selectedIndex = (selectedIndex - 1 + items.length) % items.length;
        actualizarSeleccion(items);
      }
    } else if (e.key === "Enter") {
      e.preventDefault();
      if (selectedIndex >= 0 && items[selectedIndex]) {
        const link = items[selectedIndex].querySelector("a");
        if (link) {
          window.location.href = link.href;
        }
      }
    }
  });

  document.addEventListener("click", (e) => {
    if (!e.target.closest(".dropdown")) {
      lista.classList.remove("show");
      selectedIndex = -1;
    }
  });

  lista.addEventListener("click", (e) => e.stopPropagation());

  function buscar(query) {
    fetch(`SvBuscador?query=${encodeURIComponent(query)}`)
      .then(res => {
        if (!res.ok) throw new Error("Error en la respuesta del servidor");
        return res.json();
      })
      .then(data => {
        lista.innerHTML = "";
        selectedIndex = -1;

        if (data.length === 0) {
          lista.innerHTML = "<li class='dropdown-item text-muted'>Sin resultados</li>";
        } else {
          data.forEach(p => {
            const li = document.createElement("li");
            li.className = "dropdown-item";
            li.innerHTML = `
              <a class="text-decoration-none text-dark"
                 href="SvInfoPropuesta?titulo=${encodeURIComponent(p.titulo)}">
                <p class="m-0">${p.titulo}</p>
              </a>`;
            lista.appendChild(li);
          });
        }

        lista.classList.add("show");
      })
      .catch(err => {
        console.error("Error:", err);
        lista.innerHTML = "<li class='dropdown-item text-danger'>Error al buscar</li>";
        lista.classList.add("show");
      });
  }

  function actualizarSeleccion(items) {
    items.forEach((item, i) => {
      if (i === selectedIndex) {
        item.classList.add("active");
        item.scrollIntoView({ block: "nearest" }); // hace scroll si hay overflow
      } else {
        item.classList.remove("active");
      }
    });
  }
});