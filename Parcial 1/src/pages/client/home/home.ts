import { PRODUCTS, getCategories } from "../../../data/data";
import type { ICategoria } from "../../../types/categoria";
import type { IProduct } from "../../../types/product";
import { addToCart, getCartItems } from "../../../utils/cart";

const productsContainer = document.querySelector<HTMLDivElement>("#products");
const categoriesContainer = document.querySelector<HTMLDivElement>("#categories");
const searchInput = document.querySelector<HTMLInputElement>("#search");
const resultMessage = document.querySelector<HTMLParagraphElement>("#result-message");
const cartCount = document.querySelector<HTMLSpanElement>("#cart-count");

let selectedCategoryId: number | null = null;
let searchText = "";

function formatPrice(price: number): string {
  return price.toLocaleString("es-AR", {
    style: "currency",
    currency: "ARS",
    maximumFractionDigits: 0,
  });
}

function updateCartCount(): void {
  const quantity = getCartItems().reduce((total, item) => total + item.cantidad, 0);

  if (cartCount) {
    cartCount.textContent = String(quantity);
  }
}

function getProductImage(product: IProduct): string {
  if (product.imagen) {
    return `../../../assets/${product.imagen}`;
  }

  return "";
}

function renderCategories(): void {
  if (!categoriesContainer) {
    return;
  }

  const categories = getCategories();

  categoriesContainer.innerHTML = `
    <button class="category-button ${selectedCategoryId === null ? "active" : ""}" data-category-id="all">
      Todas
    </button>
    ${categories.map((category: ICategoria) => `
      <button class="category-button ${selectedCategoryId === category.id ? "active" : ""}"
              data-category-id="${category.id}">
        ${category.nombre}
      </button>
    `).join("")}
  `;

  const buttons = categoriesContainer.querySelectorAll<HTMLButtonElement>(".category-button");

  buttons.forEach((button) => {
    button.addEventListener("click", () => {
      const value = button.dataset.categoryId;

      selectedCategoryId = value === "all" ? null : Number(value);
      renderCategories();
      renderProducts();
    });
  });
}

function getFilteredProducts(): IProduct[] {
  return PRODUCTS.filter((product) => {
    const matchesSearch = product.nombre
      .toLowerCase()
      .includes(searchText.toLowerCase());

    const matchesCategory =
      selectedCategoryId === null ||
      product.categorias.some((category) => category.id === selectedCategoryId);

    return matchesSearch && matchesCategory && !product.eliminado;
  });
}

function renderProducts(): void {
  if (!productsContainer || !resultMessage) {
    return;
  }

  const products = getFilteredProducts();

  if (products.length === 0) {
    productsContainer.innerHTML = "";
    resultMessage.textContent = "No se encontraron productos con esos criterios.";
    return;
  }

  resultMessage.textContent = `Se encontraron ${products.length} producto${products.length === 1 ? "" : "s"}.`;

  productsContainer.innerHTML = products.map((product) => {
    const disabled = !product.disponible || product.stock <= 0;

    return `
      <article class="product-card">
        <div class="product-image">
          <img src="${getProductImage(product)}" alt="${product.nombre}">
        </div>

        <div class="product-info">
          <span class="category">${product.categorias[0]?.nombre ?? "Sin categoría"}</span>
          <h2>${product.nombre}</h2>
          <p>${product.descripcion}</p>
          <strong>${formatPrice(product.precio)}</strong>
          <p class="stock">${disabled ? "Sin stock" : `Stock: ${product.stock}`}</p>

          <button class="add-button" data-product-id="${product.id}" ${disabled ? "disabled" : ""}>
            ${disabled ? "No disponible" : "Agregar al carrito"}
          </button>
        </div>
      </article>
    `;
  }).join("");

  const addButtons = productsContainer.querySelectorAll<HTMLButtonElement>(".add-button");

  addButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const productId = Number(button.dataset.productId);
      const product = PRODUCTS.find((item) => item.id === productId);

      if (!product) {
        return;
      }

      addToCart(product);
      updateCartCount();

      const oldText = button.textContent;
      button.textContent = "Agregado";
      setTimeout(() => {
        button.textContent = oldText;
      }, 900);
    });
  });
}

searchInput?.addEventListener("input", () => {
  searchText = searchInput.value.trim();
  renderProducts();
});

renderCategories();
renderProducts();
updateCartCount();
