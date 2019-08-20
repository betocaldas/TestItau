package corp.bcapc.usecase

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 17/08/2019 - 00:34
 */

interface ModelMapper<E, M> {
    fun fromEntity(from: E): M
    fun toEntity(from: M): E
}