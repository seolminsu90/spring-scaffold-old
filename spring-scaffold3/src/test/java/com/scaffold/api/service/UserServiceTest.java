package com.scaffold.api.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.scaffold.api.dto.UserDTO;
import com.scaffold.api.entity.QUserEntity;
import com.scaffold.api.entity.UserEntity;
import com.scaffold.api.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private JPAQueryFactory queryFactoryMock;

    @Mock
    private JPAQuery queryMock;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    private UserEntity mockUser(long userId, String name) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setName(name);
        return user;
    }

    @Test
    public void testGetUserByJpa() {
        // given
        long userId = 4L;
        String name = "Bob Smith";
        UserEntity user = mockUser(userId, name);

        // Mock 설정
        when(userRepository.findById(eq(userId))).thenReturn(Optional.of(user));

        // when
        UserDTO result = userService.getUserByJpa(userId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(name, result.getName());

        // mock 호출 검증 (횟수)
        verify(userRepository, times(1)).findById(eq(userId));
    }

    @Test
    public void testGetUserByQueryDSL() {
        // given
        long userId = 1L;
        String name = "John Doe";
        UserEntity user = mockUser(userId, name);
        QUserEntity qUser = QUserEntity.userEntity;

        UserDTO assertDto = UserDTO.fromEntity(user);

        // Mock 설정
        when(queryFactoryMock.selectFrom(QUserEntity.userEntity)).thenReturn(queryMock);
        when(queryMock.where(qUser.id.eq(userId))).thenReturn(queryMock);
        when(queryMock.fetchOne()).thenReturn(user);

        // when
        UserDTO result = userService.getUserByQueryDSL(userId);

        // then
        assertEquals(assertDto, result);

        // Mock 호출 검증
        verify(queryFactoryMock).selectFrom(any());
        verify(queryMock).where(qUser.id.eq(userId));
        verify(queryMock).fetchOne();
    }
}