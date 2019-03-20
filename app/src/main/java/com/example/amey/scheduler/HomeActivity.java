package com.example.amey.scheduler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_home);
        cardView=(CardView) findViewById(R.id.indexsnote);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity .this,keepnotes.class));
            }
        });
    mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
    mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
mDrawerLayout.addDrawerListener(mToggle);
mToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item))
        {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int id=Item.getItemId();
if(id==R.id.stud)
{
    Intent searchIntent = new Intent(HomeActivity.this,loginstud.class);
    startActivity(searchIntent);

}
else if(id == R.id.teacher){
    Intent searchIntent = new Intent(HomeActivity.this, loginteach.class);
    startActivity(searchIntent);

}
else if(id == R.id.rights){
    Intent searchIntent = new Intent(HomeActivity.this, adminrights.class);
    startActivity(searchIntent);

}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

