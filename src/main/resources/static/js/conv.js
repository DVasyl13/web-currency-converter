let myMap = new Map();
let currencies = [];

document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/currency-rates')
        .then(response => response.json())
        .then(data => {
            myMap = new Map(Object.entries(data));
            currencies = Array.from( myMap.keys() );

            let fromCurrency = document.getElementById("fromCurrency");
            let toCurrency = document.getElementById("toCurrency");

            for (let currenciesKey of currencies) {
                let el = document.createElement("option");
                el.textContent = currenciesKey;
                el.value = currenciesKey;
                fromCurrency.appendChild(el.cloneNode(true));
                toCurrency.appendChild(el);
            }
        })
        .catch(error => console.error(error));
});

function calculateResult() {
    var amount = document.getElementById("amount").value;
    var fromCurrency = document.getElementById("fromCurrency").value;
    var toCurrency = document.getElementById("toCurrency").value;
    // 500
    // 1 USD --- 36 UAH
    // => 500 * (36/1)

    // 1000
    // 3.67 AED --- 90 AFN
    // => 1000 * (90/3.67)
    var coef = myMap.get(toCurrency)/myMap.get(fromCurrency);
    var result = document.getElementById("calculation");

    result.innerHTML = String(coef * amount);
}
