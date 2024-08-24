document.addEventListener("DOMContentLoaded", function () {
    const imageInput = document.getElementById("imageInput");
    const previewImage = document.getElementById("previewImage");
    const resultOutput = document.getElementById("resultOutput");

    imageInput.addEventListener("change", function () {
        const file = imageInput.files[0];
        if (file) {
            // 이미지 미리보기 업데이트
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage.src = e.target.result;
            };
            reader.readAsDataURL(file);

            // 이미지를 선택하면 자동으로 업로드하고 결과 요청
            uploadImage(file);
        }
    });

    function uploadImage(file) {
        const formData = new FormData();
        formData.append("image", file);

        fetch("/classify", {
            method: "POST",
            body: formData,
        })
            .then(response => response.json())
            .then(data => {
                resultOutput.textContent = `분석 결과: ${JSON.stringify(data, null, 2)}`;
            })
            .catch(error => {
                console.error("Error:", error);
                resultOutput.textContent = "오류가 발생했습니다. 다시 시도해 주세요.";
            });
    }

    document.getElementById('imageInput').addEventListener('change', function(event) {
        const formData = new FormData();
        formData.append('image', event.target.files[0]);

        fetch('/classify', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    document.getElementById('resultOutput').innerText = data.error;
                } else {
                    document.getElementById('resultOutput').innerText = data.analysisResult;
                    const videoFrame = document.querySelector('.video-container iframe');
                    videoFrame.src = data.videoUrl;
                }
            })
            .catch(error => console.error('Error:', error));
    });

    function updateVideo(predictedLabel) {
        var videoUrl;

        switch(predictedLabel) {
            case "상리오":
                videoUrl = "https://www.youtube.com/embed/lwB0xB1whyA?t=483";
                break;
            case "음림":
                videoUrl = "https://www.youtube.com/embed/CowQ9rSOAmI";
                break;
            case "설지":
                videoUrl = "https://www.youtube.com/embed/LxG6_qX2SBA?t=13";
                break;
            default:
                videoUrl = "https://www.youtube.com/embed/82W7E20T6UQ"; // 기본 유튜브 영상 URL
                break;
        }

        // iframe src 속성을 업데이트
        document.getElementById("videoFrame").src = videoUrl;
    }

    function handleAnalysisResult(response) {
        var resultData = JSON.parse(response);
        document.getElementById("resultOutput").innerText = resultData.result;
        eval(resultData.videoScript);  // 동영상 업데이트를 위한 스크립트 실행
    }


});
