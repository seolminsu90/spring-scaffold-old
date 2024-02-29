package com.scaffold.api.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scaffold.api.dto.UserDTO;
import com.scaffold.api.entity.QUserEntity;
import com.scaffold.api.entity.UserEntity;
import com.scaffold.api.repository.UserRepository;
import com.scaffold.common.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserByJpa(long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("없는 유저"));
        return UserDTO.fromEntity(user);
    }


    private final JPAQueryFactory queryFactory;

    public UserDTO getUserByQueryDSL(long userId) {
        QUserEntity qUser = QUserEntity.userEntity;
        UserEntity user = queryFactory.selectFrom(qUser)
                .where(qUser.id.eq(userId))
                .fetchOne();

        if(user == null) throw new UserNotFoundException("없는 유저입니다.");

        return UserDTO.fromEntity(user);
    }
}
