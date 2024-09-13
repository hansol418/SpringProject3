package com.busanit501.springproject3.lhs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    // 아이디 : lsy
    private String username;

    private String email;
    //
    private String address;

    private String phone;

    // 실제 이름 : 예)이상용
    private String name;

    // 소셜 로그인 여부
    private boolean social;
    // 소셜 로그인한 프로필 이미지, 미디어 서버 주소
    private String profileImageServer;

    @Column(nullable = false)
    private String password;

    // 프로필 이미지, 몽고디비에 업로드된 이미지를 참조하는 ID
    @Column(name = "profile_image_id")
    private String profileImageId;

    // 멤버를 조회시 roleSet 를 같이 조회를 하기.
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public void clearRole() {
        this.roleSet.clear();
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
