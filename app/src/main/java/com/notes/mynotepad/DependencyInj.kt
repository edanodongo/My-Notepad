package com.notes.mynotepad

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Dependency Injection
// this is a very important step actually
// here we used dependency injection library dagger hilt
// it actually provide our database object and dao

    @Module
    @InstallIn(SingletonComponent ::class)
    object DatabaseModule {

        @Singleton
        @Provides
        fun provideDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "person_database"
        ).build()

        @Singleton
        @Provides
        fun provideDao(database: NoteDatabase) = database.getNotesDao()

}
