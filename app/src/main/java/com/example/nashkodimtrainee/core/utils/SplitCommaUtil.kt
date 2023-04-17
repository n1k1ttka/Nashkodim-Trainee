package com.example.nashkodimtrainee.core.utils

import java.lang.StringBuilder

fun commaSplit(x: String): String {

    val s = StringBuilder("")

    x.reversed().mapIndexed { index, c ->
        if ((index + 1) % 3 == 0 && x.reversed()[index] != '.' && x.reversed()[index] != ',' && index + 1 != x.length) {
            s.append(c)
            s.append(" ")
        } else
            s.append(c)
    }
    return s.toString().reversed()
}