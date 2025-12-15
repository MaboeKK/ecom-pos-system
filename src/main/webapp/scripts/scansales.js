/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const overlay = document.querySelector(".overlay");
const sale = document.querySelector('.sale');
const container = document.querySelector('.container');

const searchbtn = document.querySelector(".search-btn");
const searchtxt = document.querySelector(".search-txt");
const result = document.querySelector(".results");
const newuser = document.querySelector(".newuser");
const tabs = document.querySelector(".tabs");
const divs = document.querySelectorAll('.div');

sale.addEventListener("click",()=>{
    overlay.classList.toggle('non_visible');
    container.classList.toggle('non_visible');
    
});

overlay.addEventListener('click',(e)=>{
    
    container.classList.add('non_visible');
    overlay.classList.toggle('non_visible');
});

searchbtn.addEventListener('click',()=>{
    const customer = {customer: searchtxt.value};
            fetch('GetCustomerServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(customer)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                         
                            result.innerHTML = '';
                            if(data.id!=0){
                                result.innerHTML = `<div>
                                                    <div class="names">
                                                        <span class="name-span">${data.name}</span>
                                                        <span class="surname-span">${data.surname}</span>    
                                                    </div>
                                                    <div class="contacts">
                                                        <div class="email">${data.email}</div>
                                                        <div class="phone">${data.phone.toString().slice(0,3)+" "+data.phone.toString().slice(3,6)+" "+data.phone.toString().slice(6,9)}</div>    
                                                    </div>
                                                    <div>
                                                    </div>
                                                        <a class="laybuy-btn" href="CreateSaleServlet?customer=${data.id}&method=cash">Cash</a>
                                                        <a class="laybuy-btn" href="CreateSaleServlet?customer=${data.id}&method=card">Card</a>
                                                        <a class="laybuy-btn" href="CreateOrderServlet?customer=${data.id}">Laybuy</a>
                                                    </div>`;                                
                            }else{
                                result.innerHTML = `<div class="not_customer">${"Customer not registered"}`;
                            }   
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });     
});

document.querySelector('.tabs').addEventListener('click', (e) => {
    let target = e.target;

    // Check if the clicked element is a tab
    if (target.classList.contains('tab')) {
        // Hide all divs
        divs.forEach((d) => {
            d.classList.add('non_visible');
        });
        result.innerHTML = '';
        searchtxt.textContent = '';
        // Show the div corresponding to the clicked tab
        const num = target.dataset.num;
        const activeDiv = document.querySelector(`.div_${num}`);
        if (activeDiv) {
            activeDiv.classList.remove('non_visible');
        }
    }
});