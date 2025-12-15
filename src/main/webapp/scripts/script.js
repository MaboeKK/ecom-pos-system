        
const items = [];
const list = document.querySelector(".item_list");
const price = document.querySelector(".price");

let total = 0;
        
Quagga.init({
    inputStream : {
    name : "Live",
    type : "LiveStream",
    target: document.querySelector('#camera')    // Or '#yourElement' (optional)
    },
    decoder : {
        readers : ["code_128_reader"]
    },
    frequency: 1, // Adjust this value to reduce the processing rate
    numOfWorkers: 4
    }, function(err) {
        if (err) {
            console.log(err);
            return;
        }
        console.log("Initialization finished. Ready to start");
        Quagga.start();
    });
  
Quagga.onDetected(function(data){
    console.log(data.codeResult.code);
    const sentdata = { product: data.codeResult.code };

    fetch('ScanProductServlet', {
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

                if(data.state=='true'){    
                    items.push(data);
                    total = total + Number(data.price);

                    console.log(data);
                    
                        let item = `<li class="item" data-id=${data.id}>
                                    <div class="name_container">
                                        <div class="name">${data.name}</div>
                                    <div class="item_price">R ${data.price}.00</div>  
                                </div>
                               <div class="remove_container">
                                    <div class="remove" data-id=${data.id} data-price=${data.price}>
                                        
                                    </div>
                               </div>
                            </li>`;

                        list.insertAdjacentHTML('beforeend',item);
                        let newItem = list.lastElementChild;
                        newItem.scrollIntoView({ behavior: 'smooth' });
                        price.innerHTML = `R ${total}`;                    
                }
                if(data.state=="false"){
                                const toast = document.createElement('div');
                                toast.className = `toast show`;

                                toast.innerHTML = '<span>reached maximum number for '+data.name+'</span>';

                                document.body.appendChild(toast);

                                // Automatically hide the toast after 3 seconds
                                setTimeout(() => {
                                    toast.classList.remove('show');
                                    setTimeout(() => toast.remove(), 300); // Remove after fade-out
                                }, 3000);                    
                }

            })
            .catch(error => {
                console.error('Error:', error);
            });                
            });