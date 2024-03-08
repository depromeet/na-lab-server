package me.nalab.user.application.port.out.persistence;

import me.nalab.user.domain.user.User;

public interface UserGetPort {

    User getById(Long id);
}
