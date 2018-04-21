% Create UDP object 'udpConn' 
udpConn = udp('192.168.0.103', 'RemotePort', 4445, 'LocalPort', 8845); 

% Set size of receiving buffer, if needed. 
% set(u, 'InputBufferSize', 30000);

% Open connection to the server. 
fopen(udpConn);
disp('start conn');
i = 10;
global Vzad
global dv
Vzad = 0.1;
dv = 0;
try

    while(i>0)
        speedFromModel = updateModel(Vzad);
        % Send data to the GW
        msg = strcat(num2str(dv)," ", num2str(sprintf('%.2f',speedFromModel)));
        
        disp(msg)
        fprintf(udpConn, msg); 

        % Pause for the communication delay 
        pause(1) 

        % Receive data from GW 
        while (get(udpConn, 'BytesAvailable') > 0) 
        % udpConn.BytesAvailable 
        disp('Data from GW: ')
        data = fscanf(udpConn);
        Vzad = getSpeedFromConn(data);
        disp(Vzad)
        dv = getAngleFromConn(data);
        disp(data)
        end 
        i = i-1;
    end
    
catch
    warning('Problem with conn.');
    closeConn(udpConn);

end

% Disconnect and clean up the server connection.
closeConn(udpConn)
disp('end conn')

function closeConn(u)
    fclose(u); 
    delete(u); 
    clear u
end

function speedFromModel=updateModel(v)
    x0 = [v; 0; 0; 0; 0; 0];
    [T_n,X_n] = ode45(@NonLinFun_fr,[0 300],x0);
    figure(1)
    plot(T_n,X_n(:,1),'b','Linewidth',2)
    grid on
    xlabel('time'),ylabel('Vx')
    speedFromModel = mean(X_n(:,1));
    % disp(speedFromModel);
    % disp(X_n(:,1))
end

function speed=getSpeedFromConn(data)
    dataArray = strsplit(data);
    speed = dataArray(2);
    speed = str2double(speed);
end

function angle=getAngleFromConn(data)
    dataArray = strsplit(data);
    angle = dataArray(1);
    angle = str2double(angle);
end