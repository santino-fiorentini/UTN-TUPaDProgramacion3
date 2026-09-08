import './style.css'

const valor = document.querySelector<HTMLParagraphElement>('#valor')
const btnSumar = document.querySelector<HTMLButtonElement>('#btn-sumar')
const btnRestar = document.querySelector<HTMLButtonElement>('#btn-restar')

let contador: number = 0

const actualizarValor = (): void => {
    valor!.textContent = String(contador)
}

btnSumar!.addEventListener('click', () => {
    contador += 1
    actualizarValor()
})

btnRestar!.addEventListener('click', () => {
    contador -= 1
    actualizarValor()
})

actualizarValor()