package com.mte.infrastructurebase.interfaces

interface IRule<T> {

    fun validate(value: T?) : Any?
    fun getMsgStringRes(): Int?
    fun getMsgString(): String?
}