const locBtn = document.querySelector("#location");

function fetchLocation(){
    async function getCurrentLocation() {
        return new Promise((resolve, reject) => {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(resolve, reject);
            } else {
                alert("위치를 불러올 수 없습니다.");
            }
        });
    }

    async function main() {
        try {
            const position = await getCurrentLocation();
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            document.getElementById("lat").value = latitude;
            document.getElementById("lnt").value = longitude;
            // $("#lat").val(latitude);
            // $("#lnt").val(longitude);
        } catch (error) {
            console.log(error);
        }
    }
    main();
}

function displayNone(){
    alert("??")
    document.getElementById("before").style.display = "none";
}

locBtn.addEventListener('click',fetchLocation);
