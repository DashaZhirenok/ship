%listing 5
function Dx = NonLinFun_fr(t,x)
global Vzad; % m/c
global dv;
% u(1) = Vx,     u(2) = Vy,   u(3) = Wx,  u(4) = Wz,
% u(5) = ksi,    u(6) = dzeta,
% u(7) = teta,   u(8) = phi,
% u(9) = delta,  u(10)= Vzad;
% u(11)= Fy.
%Vx,Vy,Wz,ksi,dzeta,phi,
z = rippi_fr([x(1),x(2),0,x(3),x(4),x(5),0,x(6),dv,Vzad,0]);
Dx = [z(1:2);z(4);z(5);z(6);z(8)];
% 1-dVx/dt         2-dVy/dt     
% 3-dWx/dt         4-dWz/dt     
% 5(x4)-dksi/dt    6(x5)-ddzeta/dt
% 7(0)-dteta/dt    8(x6)-dphi/dt
