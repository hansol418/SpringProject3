$(function () {
    // 메인 슬라이드 설정
    $("#main-slide .lazy").slick({
        lazyLoad: 'ondemand', // 필요할 때 이미지 로드
        infinite: true,       // 무한 반복
        autoplay: true,       // 자동 재생
        autoplaySpeed: 5000   // 자동 재생 속도 (5초)
    });

    // 공구 이미지 롤오버 효과
    $('#Quick .Quick-menu a').each(function () {
        var img = $(this).find('img');
        var src_off = img.attr('src');
        var src_on = src_off.replace('off', 'on');

        $(this).hover(
            function () {
                img.attr('src', src_on); // 마우스 오버 시 이미지 변경
            },
            function () {
                img.attr('src', src_off); // 마우스 아웃 시 원래 이미지로 복원
            }
        );
    });

    // 모바일 메뉴 열기/닫기
    $('.mob_menu_open').click(function () {
        $('#mob_gnb').addClass('on');
        $('.layer_bg').addClass('on');
        scrollDisable();
    });

    $('.mob_menu_close, .layer_bg').click(function () {
        $('#mob_gnb').removeClass('on');
        $('.layer_bg').removeClass('on');
        scrollAble();
    });

    // 모바일 메뉴 내 서브 메뉴 열기/닫기
    var menu = $('.mob_menu_wrap a.tit');
    var submenu = $('.mob_menu_wrap a.sub_tit');

    menu.click(function () {
        menu.not($(this)).removeClass('on');
        $(this).toggleClass('on');
        menu.not($(this)).next('.sub_menu').slideUp('on');
        $(this).next('.sub_menu').slideToggle('on');
    });

    submenu.click(function () {
        submenu.not($(this)).removeClass('on');
        $(this).toggleClass('on');
        submenu.not($(this)).next('.depth_menu').slideUp('on');
        $(this).next('.depth_menu').slideToggle('on');
    });

    // 공구 이미지 갤러리 슬라이드 설정
    $("#tool-gallery .regular").slick({
        arrows: true,
        infinite: true,
        slidesToShow: 4,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,  // 3초마다 자동 전환
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1
                }
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });

    // 슬라이드 재생/정지 버튼
    var isPaused = false;
    $('.btn_pause').click(function () {
        if (!isPaused) {
            $('.btn_pause').addClass('on');
            $('#tool-gallery .regular').slick('slickPause');
            isPaused = true;
        } else {
            $('.btn_pause').removeClass('on');
            $('#tool-gallery .regular').slick('slickPlay');
            isPaused = false;
        }
    });

    // 공구 카테고리 필터링 (예: 드롭다운 선택 시 필터 적용)
    $('#toolCategory').change(function () {
        const selectedCategory = $(this).val();
        window.location.href = `/tools/filter?category=${selectedCategory}`;
    });

    // 공구 정보 탭 전환
    $('.tabSet').each(function () {
        var anchor = $(this).find('.tabs a');
        var anchor_on = $(this).find('.tabs a.on');
        var phref_on = anchor_on.attr('phref');
        var this_panel = $(this).find('.panel');

        $(phref_on).show();
        anchor.each(function () {
            var phref = $(this).attr('phref');

            $(this).click(function () {
                this_panel.hide();
                anchor.removeClass('on');
                $(phref).show();
                $(this).addClass('on');
            });
        });
    });

    // 공구 사용 후기 롤링
    function review_scroll() {
        $('#rolling_review li:first').slideUp(function () {
            $(this).appendTo($('#rolling_review')).slideDown();
        });
    }

    setInterval(function () {
        review_scroll();
    }, 4000); // 4초마다 롤링

    // 패밀리 사이트 드롭다운
    $(".family").click(function () {
        $(".family ul").toggleClass("opacity");
        $(".icon").toggleClass("rotate");
    });

    // 스크롤 제어 함수 (필요 시 정의해야 함)
    function scrollDisable() {
        // 스크롤을 비활성화하는 로직을 추가하세요.
    }

    function scrollAble() {
        // 스크롤을 다시 활성화하는 로직을 추가하세요.
    }
});
