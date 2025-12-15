/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const products = document.querySelectorAll(".product");
const total = document.querySelector(".msg");
const wind = document.getElementById("id");
let productId;
let price;
products.forEach(function(product) {
    product.addEventListener('click', function() {
        if (event.target.classList.contains('add')) {
            productId = event.target.dataset.product;
            price = event.target.dataset.price;
            const sentdata = { product: productId ,price: price};
            fetch('PlusServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(sentdata)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                            console.log(wind);
                            console.log(data);
                            const count = document.getElementById(`${data.id}`);
                            if(data.count==1){
                                count.classList.remove("count_visible");
                            }
                            if(data.state=="false"){
                                const toast = document.createElement('div');
                                toast.className = `toast show`;

                                toast.innerHTML = '<span>'+data.product+' reached maximum number of selects</span>';

                                wind.appendChild(toast);

                                // Automatically hide the toast after 3 seconds
                                setTimeout(() => {
                                    toast.classList.remove('show');
                                    setTimeout(() => toast.remove(), 300); // Remove after fade-out
                                }, 3000);
                            }
                            count.innerHTML = data.count;
                            total.innerHTML = "R"+data.price+".00";
                            
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });  

                }
        else if(event.target.classList.contains('minus')){
            productId = event.target.dataset.product;
            price = event.target.dataset.price;
            const sentdata = { product: productId ,price: price};
            fetch('MinusServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(sentdata)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                            console.log(data);
                            const count = document.getElementById(`${data.id}`);
                            if(data.count==0){
                                count.classList.add("count_visible");
                            }
                            
                            if(data.state=="false"){
                                const toast = document.createElement('div');
                                toast.className = `toast show`;

                                toast.innerHTML = '<span>Not a valid operation</span>';

                                wind.appendChild(toast);

                                // Automatically hide the toast after 3 seconds
                                setTimeout(() => {
                                    toast.classList.remove('show');
                                    setTimeout(() => toast.remove(), 300); // Remove after fade-out
                                }, 3000);                                
                            }
                            count.innerHTML = data.count;
                            total.innerHTML = "R"+data.price+".00";
                            
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        }); 
        }
    });
});