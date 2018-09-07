package com.saikrishnarao.smartroomba

enum class RoombaControl(val byteArray: ByteArray) {
    TEST_CONNECTION(byteArrayOf(84, 69)),
    START_BAUD(byteArrayOf(83, 66)),
    FULL_MODE(byteArrayOf(70, 77)),
    SAFE_MODE(byteArrayOf(83, 77)),
    UP(byteArrayOf(85, 80)),
    DOWN(byteArrayOf(68, 78)),
    LEFT(byteArrayOf(76, 84)),
    RIGHT(byteArrayOf(82, 84)),
    CLEAN(byteArrayOf(67, 76)),
    DOCK(byteArrayOf(83, 84))
}