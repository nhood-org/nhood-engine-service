package com.h8.nh.service.app.command;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class AddDataCommandResult {
    private final UUID uuid;

    public AddDataCommandResult(UUID uuid) {
        this.uuid = uuid;
    }

    public static AddDataCommandResult of(UUID uuid) {
        return new AddDataCommandResult(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }
}
