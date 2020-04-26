package ru.relex.common.models;

import java.util.List;

public interface CurrentUser {
    int getId();
    String getUsername();
    List<Role> getRoles();
}
