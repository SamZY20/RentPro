package com.example.rentpro.di

import com.example.rentpro.domain.repository.AuthRepository
import com.example.rentpro.domain.repository.AuthRepositoryImpl
import com.example.rentpro.domain.repository.PropertyRepository
import com.example.rentpro.domain.repository.PropertyRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun providePropertyRepository(impl: PropertyRepositoryImpl): PropertyRepository = impl
}
