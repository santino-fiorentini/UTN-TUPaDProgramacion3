import {
  getCartItems,
  getCartTotal,
  removeFromCart,
  updateCartQuantity,
} from "../../../utils/cart";

const cartContainer = document.querySelector<HTMLElement>("#cart-container");
const totalElement = document.querySelector<HTMLElement>("#cart-total");

function formatPrice(price: number): string {
  return price.toLocaleString("es-AR", {
    style: "currency",
    currency: "ARS",
    maximumFractionDigits: 0,
  });
}

function renderCart(): void {
  if (!cartContainer || !totalElement) {
    return;
  }

  const items = getCartItems();

  if (items.length === 0) {
    cartContainer.innerHTML = `
      <div class="empty-cart">
        <h2>El carrito está vacío</h2>
        <p>Agregá productos desde el catálogo.</p>
        <a href="../home/home.html">Ir al catálogo</a>
      </div>
    `;

    totalElement.textContent = formatPrice(0);
    return;
  }

  cartContainer.innerHTML = items.map((item) => {
    const subtotal = item.precio * item.cantidad;

    return `
      <article class="cart-item">
        <div class="item-image">
          <img src="../../../assets/${item.imagen}" alt="${item.nombre}">
        </div>

        <div class="item-info">
          <h2>${item.nombre}</h2>
          <p>Precio unitario: ${formatPrice(item.precio)}</p>

          <label>
            Cantidad:
            <input class="quantity-input"
                   type="number"
                   min="1"
                   value="${item.cantidad}"
                   data-product-id="${item.id}">
          </label>
        </div>

        <div class="item-price">
          <strong>${formatPrice(subtotal)}</strong>
          <button class="remove-button" data-product-id="${item.id}">Eliminar</button>
        </div>
      </article>
    `;
  }).join("");

  totalElement.textContent = formatPrice(getCartTotal());

  const quantityInputs =
    cartContainer.querySelectorAll<HTMLInputElement>(".quantity-input");

  quantityInputs.forEach((input) => {
    input.addEventListener("change", () => {
      const id = Number(input.dataset.productId);
      const quantity = Number(input.value);

      updateCartQuantity(id, quantity);
      renderCart();
    });
  });

  const removeButtons =
    cartContainer.querySelectorAll<HTMLButtonElement>(".remove-button");

  removeButtons.forEach((button) => {
    button.addEventListener("click", () => {
      const id = Number(button.dataset.productId);

      removeFromCart(id);
      renderCart();
    });
  });
}

renderCart();
