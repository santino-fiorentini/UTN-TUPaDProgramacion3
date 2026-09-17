import type { ICartItem, IProduct } from "../types/product";

const CART_KEY = "cart";

export function getCartItems(): ICartItem[] {
  const data = localStorage.getItem(CART_KEY);

  if (!data) {
    return [];
  }

  try {
    const items: ICartItem[] = JSON.parse(data);
    return Array.isArray(items) ? items : [];
  } catch {
    return [];
  }
}

function saveCart(items: ICartItem[]): void {
  localStorage.setItem(CART_KEY, JSON.stringify(items));
}

export function addToCart(product: IProduct): void {
  const items = getCartItems();
  const existingItem = items.find((item) => item.id === product.id);

  if (existingItem) {
    existingItem.cantidad++;
  } else {
    items.push({
      id: product.id,
      nombre: product.nombre,
      precio: product.precio,
      cantidad: 1,
      imagen: product.imagen,
    });
  }

  saveCart(items);
}

export function updateCartQuantity(id: number, cantidad: number): void {
  const items = getCartItems();
  const item = items.find((cartItem) => cartItem.id === id);

  if (!item) {
    return;
  }

  if (cantidad <= 0) {
    const newItems = items.filter((cartItem) => cartItem.id !== id);
    saveCart(newItems);
    return;
  }

  item.cantidad = cantidad;
  saveCart(items);
}

export function removeFromCart(id: number): void {
  const items = getCartItems().filter((item) => item.id !== id);
  saveCart(items);
}

export function getCartTotal(): number {
  return getCartItems().reduce(
    (total, item) => total + item.precio * item.cantidad,
    0
  );
}
